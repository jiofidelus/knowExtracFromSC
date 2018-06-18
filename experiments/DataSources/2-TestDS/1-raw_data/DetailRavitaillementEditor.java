package org.imogene.epicam.client.ui.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.web.bindery.requestfactory.shared.Receiver;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailRavitaillementProxy;
import org.imogene.epicam.shared.request.DetailRavitaillementRequest;

import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.web.shared.request.ImogEntityRequest;
import com.google.web.bindery.requestfactory.shared.Request;

import org.imogene.web.client.event.FieldValueChangeEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.*;
import org.imogene.web.client.ui.field.binary.*;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.field.relation.multi.ImogMultiRelationBox;
import org.imogene.web.client.ui.field.relation.single.ImogSingleRelationBox;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.client.ui.panel.WrapperPanel;
import org.imogene.web.client.util.NumericUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import org.imogene.epicam.client.ui.workflow.panel.RavitaillementFormPanel;
import org.imogene.epicam.client.event.save.SaveRavitaillementEvent;
import org.imogene.epicam.client.dataprovider.RavitaillementDataProvider;
import org.imogene.epicam.client.ui.filter.RavitaillementFilterPanel;
import org.imogene.epicam.shared.proxy.RavitaillementProxy;
import org.imogene.epicam.client.ui.workflow.panel.SortieLotFormPanel;
import org.imogene.epicam.client.event.save.SaveSortieLotEvent;
import org.imogene.epicam.client.dataprovider.SortieLotDataProvider;
import org.imogene.epicam.client.ui.filter.SortieLotFilterPanel;
import org.imogene.epicam.shared.proxy.SortieLotProxy;
import org.imogene.epicam.client.ui.editor.nested.SortieLotEditorNestedRow;
import org.imogene.epicam.client.ui.workflow.panel.EntreeLotFormPanel;
import org.imogene.epicam.client.event.save.SaveEntreeLotEvent;
import org.imogene.epicam.client.dataprovider.EntreeLotDataProvider;
import org.imogene.epicam.client.ui.filter.EntreeLotFilterPanel;
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.epicam.client.ui.editor.nested.EntreeLotEditorNestedRow;

/**
 * Editor that provides the UI components that allow a DetailRavitaillementProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class DetailRavitaillementEditor extends Composite
		implements
			Editor<DetailRavitaillementProxy>,
			HasEditorDelegate<DetailRavitaillementProxy>,
			HasEditorErrors<DetailRavitaillementProxy> {

	interface Binder extends UiBinder<Widget, DetailRavitaillementEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<DetailRavitaillementProxy> delegate;

	private DetailRavitaillementProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<RavitaillementProxy> ravitaillement;
	private RavitaillementDataProvider ravitaillementDataProvider;

	/* Sortie section widgets */
	@UiField
	@Ignore
	FieldGroupPanel sortieSection;
	@UiField(provided = true)
	SortieLotEditorNestedRow sortieLot;
	private SortieLotDataProvider sortieLotDataProvider;

	/* Entree section widgets */
	@UiField
	@Ignore
	FieldGroupPanel entreeSection;
	@UiField(provided = true)
	EntreeLotEditorNestedRow entreeLot;
	private EntreeLotDataProvider entreeLotDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public DetailRavitaillementEditor(EpicamRequestFactory factory,
			boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public DetailRavitaillementEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.detailRavitaillement_group_description());
		ravitaillement.setLabel(NLS.constants()
				.detailRavitaillement_field_ravitaillement());
		// hidden field
		ravitaillement.setVisible(false);

		/* Sortie section widgets */
		sortieSection.setGroupTitle(NLS.constants()
				.detailRavitaillement_group_sortie());

		/* Entree section widgets */
		entreeSection.setGroupTitle(NLS.constants()
				.detailRavitaillement_group_entree());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  ravitaillement */
		ravitaillementDataProvider = new RavitaillementDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			ravitaillement = new ImogSingleRelationBox<RavitaillementProxy>(
					ravitaillementDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateRavitaillement()
					&& AccessManager.canEditRavitaillement())
				ravitaillement = new ImogSingleRelationBox<RavitaillementProxy>(
						ravitaillementDataProvider, EpicamRenderer.get());
			else
				ravitaillement = new ImogSingleRelationBox<RavitaillementProxy>(
						false, ravitaillementDataProvider, EpicamRenderer.get());
		}

		/* field  sortieLot */
		sortieLot = new SortieLotEditorNestedRow(requestFactory);

		/* field  entreeLot */
		entreeLot = new EntreeLotEditorNestedRow(requestFactory);

	}

	/**
	 * Sets the edition mode
	 * @param isEdited true to enable the edition of the form
	 */
	public void setEdited(boolean isEdited) {

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		/* Description section widgets */
		// readonly field
		ravitaillement.setEdited(false);

		/* Sortie section widgets */
		sortieLot.setEdited(isEdited);

		/* Entree section widgets */
		entreeLot.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadDetailRavitaillementDescription())
			descriptionSection.setVisible(false);

		/* Sortie section widgets visibility */
		if (!AccessManager.canReadDetailRavitaillementSortie())
			sortieSection.setVisible(false);

		/* Entree section widgets visibility */
		if (!AccessManager.canReadDetailRavitaillementEntree())
			entreeSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditDetailRavitaillementDescription())
			descriptionSection.setVisible(false);

		/* Sortie section widgets visibility */
		if (!AccessManager.canEditDetailRavitaillementSortie())
			sortieSection.setVisible(false);

		/* Entree section widgets visibility */
		if (!AccessManager.canEditDetailRavitaillementEntree())
			entreeSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(DetailRavitaillementRequest ctx) {
		sortieLot.setRequestContextForListEditors(ctx);
		entreeLot.setRequestContextForListEditors(ctx);
	}

	/**
	 * Manages editor updates when a field value changes
	 */
	private void setFieldValueChangeHandler() {

		registrations.add(requestFactory.getEventBus().addHandler(
				FieldValueChangeEvent.TYPE,
				new FieldValueChangeEvent.Handler() {
					@Override
					public void onValueChange(ImogField<?> field) {

						// field dependent visibility management
						computeVisibility(field, false);

					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {
		sortieLot.computeVisibility(source, allValidation);
		entreeLot.computeVisibility(source, allValidation);

	}

	/**
	 * Setter to inject a Ravitaillement value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRavitaillement(RavitaillementProxy value, boolean isLocked) {
		ravitaillement.setLocked(isLocked);
		ravitaillement.setValue(value);

	}

	/** Widget update for field ravitaillement */
	private void clearRavitaillementWidget() {
		ravitaillement.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Ravitaillement */
		ravitaillement.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (ravitaillement.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					RavitaillementFormPanel form = new RavitaillementFormPanel(
							requestFactory, ravitaillement.getValue().getId(),
							relationPopup, "ravitaillement");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Ravitaillement */
		ravitaillement.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				RavitaillementFormPanel form = new RavitaillementFormPanel(
						requestFactory, null, relationPopup, "ravitaillement");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Ravitaillement is created or updated from the relation field Ravitaillement */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveRavitaillementEvent.TYPE,
				new SaveRavitaillementEvent.Handler() {
					@Override
					public void saveRavitaillement(RavitaillementProxy value) {
						ravitaillement.setValue(value);
					}
					@Override
					public void saveRavitaillement(RavitaillementProxy value,
							String initField) {
						if (initField.equals("ravitaillement"))
							ravitaillement.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the DetailRavitaillementProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public DetailRavitaillementProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the DetailRavitaillementProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(DetailRavitaillementProxy editedValue) {
		this.editedValue = editedValue;

	}

	/**
	 *
	 */
	public void raiseNotUniqueError(String field) {
		delegate.recordError(BaseNLS.messages().error_not_unique(), null, field);
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

		// sortieLot nested form shall be validated
		sortieLot.validateFields();
		// entreeLot nested form shall be validated
		entreeLot.validateFields();
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		ravitaillement.setLabelWidth(width);

		/* Sortie field group */

		/* Entree field group */

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		ravitaillement.setBoxWidth(width);

		/* Sortie field group */

		/* Entree field group */

	}

	@Override
	public void setDelegate(EditorDelegate<DetailRavitaillementProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

				}
			}
		}
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setRelationHandlers();
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
