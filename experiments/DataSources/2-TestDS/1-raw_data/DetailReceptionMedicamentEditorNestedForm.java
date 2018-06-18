package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailReceptionMedicamentProxy;

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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import org.imogene.epicam.client.ui.workflow.panel.CommandeFormPanel;
import org.imogene.epicam.client.event.save.SaveCommandeEvent;
import org.imogene.epicam.client.dataprovider.CommandeDataProvider;
import org.imogene.epicam.client.ui.filter.CommandeFilterPanel;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.client.ui.workflow.panel.DetailCommandeMedicamentFormPanel;
import org.imogene.epicam.client.event.save.SaveDetailCommandeMedicamentEvent;
import org.imogene.epicam.client.dataprovider.DetailCommandeMedicamentDataProvider;
import org.imogene.epicam.client.ui.filter.DetailCommandeMedicamentFilterPanel;
import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;
import org.imogene.epicam.client.ui.workflow.panel.EntreeLotFormPanel;
import org.imogene.epicam.client.event.save.SaveEntreeLotEvent;
import org.imogene.epicam.client.dataprovider.EntreeLotDataProvider;
import org.imogene.epicam.client.ui.filter.EntreeLotFilterPanel;
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.epicam.client.ui.editor.nested.EntreeLotEditorNestedRow;

/**
 * Editor that provides the UI components that allow a DetailReceptionMedicamentProxy to be viewed and edited
 * as a Nested Form
 * @author MEDES-IMPS
 */
public class DetailReceptionMedicamentEditorNestedForm extends Composite
		implements
			Editor<DetailReceptionMedicamentProxy>,
			HasEditorDelegate<DetailReceptionMedicamentProxy>,
			HasEditorErrors<DetailReceptionMedicamentProxy> {

	interface Binder
			extends
				UiBinder<Widget, DetailReceptionMedicamentEditorNestedForm> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<DetailReceptionMedicamentProxy> delegate;

	private boolean hideButtons = false;
	private int index = 0;
	private boolean isNewProxy = false;
	private boolean isClearImageActive = false;

	@UiField
	Image clearImage;

	@UiField
	@Ignore
	FieldGroupPanel detailReceptionMedicamentSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CommandeProxy> commande;
	private CommandeDataProvider commandeDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DetailCommandeMedicamentProxy> detailCommande;
	private DetailCommandeMedicamentDataProvider detailCommandeDataProvider;
	@UiField(provided = true)
	EntreeLotEditorNestedRow entreeLot;
	private EntreeLotDataProvider entreeLotDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public DetailReceptionMedicamentEditorNestedForm(
			EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		initWidget(BINDER.createAndBindUi(this));

		clearImage.setTitle(BaseNLS.constants().button_remove());
		clearImage
				.setUrl(GWT.getModuleBaseURL() + "images/relation_remove.png");

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public DetailReceptionMedicamentEditorNestedForm(
			EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	public void properties() {

		detailReceptionMedicamentSection.setGroupTitle(NLS.constants()
				.detailReceptionMedicament_name());
		detailReceptionMedicamentSection.setLabelFontSize("12px");

		commande.setLabel(NLS.constants()
				.detailReceptionMedicament_field_commande());
		// the value of commande affects the value of other fields
		commande.notifyChanges(requestFactory.getEventBus());
		// hidden field
		commande.setVisible(false);
		detailCommande.setLabel(NLS.constants()
				.detailReceptionMedicament_field_detailCommande());
	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	public void setRelationFields() {

		/* field  commande */
		commandeDataProvider = new CommandeDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			commande = new ImogSingleRelationBox<CommandeProxy>(
					commandeDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCommande()
					&& AccessManager.canEditCommande())
				commande = new ImogSingleRelationBox<CommandeProxy>(
						commandeDataProvider, EpicamRenderer.get());
			else
				commande = new ImogSingleRelationBox<CommandeProxy>(false,
						commandeDataProvider, EpicamRenderer.get());
		}

		/* field  detailCommande */
		detailCommandeDataProvider = new DetailCommandeMedicamentDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			detailCommande = new ImogSingleRelationBox<DetailCommandeMedicamentProxy>(
					detailCommandeDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateDetailCommandeMedicament()
					&& AccessManager.canEditDetailCommandeMedicament())
				detailCommande = new ImogSingleRelationBox<DetailCommandeMedicamentProxy>(
						detailCommandeDataProvider, EpicamRenderer.get());
			else
				detailCommande = new ImogSingleRelationBox<DetailCommandeMedicamentProxy>(
						false, detailCommandeDataProvider, EpicamRenderer.get());
		}

		/* field  entreeLot */
		entreeLot = new EntreeLotEditorNestedRow(requestFactory);

	}

	/**
	 * Sets the edition mode
	 * @param isEdited true to enable the edition of the form
	 */
	public void setEdited(boolean isEdited) {

		if (isClearImageActive)
			clearImage.setVisible(isEdited);
		else
			clearImage.setVisible(false);

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		commande.setEdited(isEdited);
		detailCommande.setEdited(isEdited);
		entreeLot.setEdited(isEdited);
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadDetailReceptionMedicamentDescription())
			commande.setVisible(false);

		if (!AccessManager.canReadDetailReceptionMedicamentDescription())
			detailCommande.setVisible(false);

		if (!AccessManager.canReadDetailReceptionMedicamentDescription())
			entreeLot.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	public void setFieldEditAccess() {

		if (!AccessManager.canEditDetailReceptionMedicamentDescription())
			commande.setVisible(false);

		if (!AccessManager.canEditDetailReceptionMedicamentDescription())
			detailCommande.setVisible(false);

		if (!AccessManager.canEditDetailReceptionMedicamentDescription())
			entreeLot.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors
	 */
	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
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

						/* DetailCommande list content depends on the value of field Commande */
						if (field.equals(commande)) {
							clearDetailCommandeWidget();
							getDetailCommandeFilteredByCommande(commande
									.getValue());

						}
					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

	}

	/**
	 * Filters the content of the RelationField DetailCommande by the value of 
	 * the RelationField Commande
	 * @param commande the value of 
	 * the RelationField Commande 
	 */
	public void getDetailCommandeFilteredByCommande(CommandeProxy pCommande) {

		if (pCommande != null) {
			if (!hideButtons)
				detailCommande.hideButtons(false);
			detailCommandeDataProvider.setFilterCriteria(pCommande.getId(),
					"commande.id");
		} else {
			detailCommande.hideButtons(true);
			detailCommandeDataProvider.setFilterCriteria(null);
		}
	}

	public void setDeleteClickHandler(ClickHandler handler) {
		//registrations.add(clearImage.addClickHandler(handler));
		clearImage.addClickHandler(handler);
		isClearImageActive = true;
	}

	/**
	 * Setter to inject a Commande value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCommande(CommandeProxy value, boolean isLocked) {
		commande.setLocked(isLocked);
		commande.setValue(value);

		// Field DetailCommande depends on the value of field commande
		clearDetailCommandeWidget();
		getDetailCommandeFilteredByCommande(value);
	}

	/** Widget update for field commande */
	private void clearCommandeWidget() {
		commande.clear();
	}

	/**
	 * Setter to inject a DetailCommandeMedicament value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDetailCommande(DetailCommandeMedicamentProxy value,
			boolean isLocked) {
		detailCommande.setLocked(isLocked);
		detailCommande.setValue(value);

	}

	/** Widget update for field detailCommande */
	private void clearDetailCommandeWidget() {
		detailCommande.clear();

	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Commande */
		commande.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (commande.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CommandeFormPanel form = new CommandeFormPanel(
							requestFactory, commande.getValue().getId(),
							relationPopup, "commande");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Commande */
		commande.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CommandeFormPanel form = new CommandeFormPanel(requestFactory,
						null, relationPopup, "commande");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Commande is created or updated from the relation field Commande */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCommandeEvent.TYPE, new SaveCommandeEvent.Handler() {
					@Override
					public void saveCommande(CommandeProxy value) {
						commande.setValue(value);
					}
					@Override
					public void saveCommande(CommandeProxy value,
							String initField) {
						if (initField.equals("commande"))
							commande.setValue(value, true);
					}
				}));

		/* 'Information' button for field DetailCommande */
		detailCommande.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (detailCommande.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					DetailCommandeMedicamentFormPanel form = new DetailCommandeMedicamentFormPanel(
							requestFactory, detailCommande.getValue().getId(),
							relationPopup, "detailCommande");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field DetailCommande */
		detailCommande.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				DetailCommandeMedicamentFormPanel form = new DetailCommandeMedicamentFormPanel(
						requestFactory, null, relationPopup, "detailCommande");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a DetailCommandeMedicament is created or updated from the relation field DetailCommande */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveDetailCommandeMedicamentEvent.TYPE,
				new SaveDetailCommandeMedicamentEvent.Handler() {
					@Override
					public void saveDetailCommandeMedicament(
							DetailCommandeMedicamentProxy value) {
						detailCommande.setValue(value);
					}
					@Override
					public void saveDetailCommandeMedicament(
							DetailCommandeMedicamentProxy value,
							String initField) {
						if (initField.equals("detailCommande"))
							detailCommande.setValue(value, true);
					}
				}));

	}

	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	public int getIndex() {
		return index;
	}

	public boolean isNewProxy() {
		return isNewProxy;
	}

	public void setNewProxy(boolean isNewProxy) {
		this.isNewProxy = isNewProxy;
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

		// commande is a required field
		if (commande.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"commande");
		// detailCommande is a required field
		if (detailCommande.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"detailCommande");
		// entreeLot nested form shall be validated
		entreeLot.validateFields();
	}

	/**
	 */
	public void updatedetailReceptionMedicamentSectionLabel(String label) {
		detailReceptionMedicamentSection.setGroupTitle(label);
	}

	@Override
	public void setDelegate(
			EditorDelegate<DetailReceptionMedicamentProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> commandeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> detailCommandeFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("commande"))
						commandeFieldErrors.add(error);
					if (field.equals("detailCommande"))
						detailCommandeFieldErrors.add(error);
				}
			}
			if (commandeFieldErrors.size() > 0)
				commande.showErrors(commandeFieldErrors);
			if (detailCommandeFieldErrors.size() > 0)
				detailCommande.showErrors(detailCommandeFieldErrors);
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
