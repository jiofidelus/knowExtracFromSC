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
import org.imogene.epicam.shared.proxy.RendezVousProxy;
import org.imogene.epicam.shared.request.RendezVousRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.CasTuberculoseFormPanel;
import org.imogene.epicam.client.event.save.SaveCasTuberculoseEvent;
import org.imogene.epicam.client.dataprovider.CasTuberculoseDataProvider;
import org.imogene.epicam.client.ui.filter.CasTuberculoseFilterPanel;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;

/**
 * Editor that provides the UI components that allow a RendezVousProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class RendezVousEditor extends Composite
		implements
			Editor<RendezVousProxy>,
			HasEditorDelegate<RendezVousProxy>,
			HasEditorErrors<RendezVousProxy> {

	interface Binder extends UiBinder<Widget, RendezVousEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<RendezVousProxy> delegate;

	private RendezVousProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CasTuberculoseProxy> casTb;
	private CasTuberculoseDataProvider casTbDataProvider;
	@UiField
	ImogDateBox dateRendezVous;
	@UiField
	ImogBooleanBox honore;
	@UiField
	ImogIntegerBox nombreSMSEnvoye;
	@UiField
	ImogTextAreaBox commentaire;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public RendezVousEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public RendezVousEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.rendezVous_group_description());
		casTb.setLabel(NLS.constants().rendezVous_field_casTb());
		dateRendezVous.setLabel(NLS.constants()
				.rendezVous_field_dateRendezVous());
		honore.setLabel(NLS.constants().rendezVous_field_honore());
		nombreSMSEnvoye.setLabel(NLS.constants()
				.rendezVous_field_nombreSMSEnvoye());
		commentaire.setLabel(NLS.constants().rendezVous_field_commentaire());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  casTb */
		casTbDataProvider = new CasTuberculoseDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			casTb = new ImogSingleRelationBox<CasTuberculoseProxy>(
					casTbDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCasTuberculose()
					&& AccessManager.canEditCasTuberculose())
				casTb = new ImogSingleRelationBox<CasTuberculoseProxy>(
						casTbDataProvider, EpicamRenderer.get());
			else
				casTb = new ImogSingleRelationBox<CasTuberculoseProxy>(false,
						casTbDataProvider, EpicamRenderer.get());
		}

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
		casTb.setEdited(isEdited);
		dateRendezVous.setEdited(isEdited);
		honore.setEdited(isEdited);
		nombreSMSEnvoye.setEdited(isEdited);
		commentaire.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadRendezVousDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditRendezVousDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(RendezVousRequest ctx) {
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

	}

	/**
	 * Setter to inject a CasTuberculose value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCasTb(CasTuberculoseProxy value, boolean isLocked) {
		casTb.setLocked(isLocked);
		casTb.setValue(value);

	}

	/** Widget update for field casTb */
	private void clearCasTbWidget() {
		casTb.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field CasTb */
		casTb.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (casTb.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
							requestFactory, casTb.getValue().getId(),
							relationPopup, "casTb");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CasTb */
		casTb.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
						requestFactory, null, relationPopup, "casTb");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CasTuberculose is created or updated from the relation field CasTb */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCasTuberculoseEvent.TYPE,
				new SaveCasTuberculoseEvent.Handler() {
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value) {
						casTb.setValue(value);
					}
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value,
							String initField) {
						if (initField.equals("casTb"))
							casTb.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the RendezVousProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public RendezVousProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the RendezVousProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(RendezVousProxy editedValue) {
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

	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		casTb.setLabelWidth(width);
		dateRendezVous.setLabelWidth(width);
		honore.setLabelWidth(width);
		nombreSMSEnvoye.setLabelWidth(width);
		commentaire.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		casTb.setBoxWidth(width);
		commentaire.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<RendezVousProxy> delegate) {
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
