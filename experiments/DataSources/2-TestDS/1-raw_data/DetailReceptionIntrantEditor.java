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
import org.imogene.epicam.shared.proxy.DetailReceptionIntrantProxy;
import org.imogene.epicam.shared.request.DetailReceptionIntrantRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.ReceptionFormPanel;
import org.imogene.epicam.client.event.save.SaveReceptionEvent;
import org.imogene.epicam.client.dataprovider.ReceptionDataProvider;
import org.imogene.epicam.client.ui.filter.ReceptionFilterPanel;
import org.imogene.epicam.shared.proxy.ReceptionProxy;
import org.imogene.epicam.client.ui.workflow.panel.CommandeFormPanel;
import org.imogene.epicam.client.event.save.SaveCommandeEvent;
import org.imogene.epicam.client.dataprovider.CommandeDataProvider;
import org.imogene.epicam.client.ui.filter.CommandeFilterPanel;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.client.ui.workflow.panel.DetailCommandeIntrantFormPanel;
import org.imogene.epicam.client.event.save.SaveDetailCommandeIntrantEvent;
import org.imogene.epicam.client.dataprovider.DetailCommandeIntrantDataProvider;
import org.imogene.epicam.client.ui.filter.DetailCommandeIntrantFilterPanel;
import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;
import org.imogene.epicam.client.ui.workflow.panel.EntreeLotFormPanel;
import org.imogene.epicam.client.event.save.SaveEntreeLotEvent;
import org.imogene.epicam.client.dataprovider.EntreeLotDataProvider;
import org.imogene.epicam.client.ui.filter.EntreeLotFilterPanel;
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.epicam.client.ui.editor.nested.EntreeLotEditorNestedRow;

/**
 * Editor that provides the UI components that allow a DetailReceptionIntrantProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class DetailReceptionIntrantEditor extends Composite
		implements
			Editor<DetailReceptionIntrantProxy>,
			HasEditorDelegate<DetailReceptionIntrantProxy>,
			HasEditorErrors<DetailReceptionIntrantProxy> {

	interface Binder extends UiBinder<Widget, DetailReceptionIntrantEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<DetailReceptionIntrantProxy> delegate;

	private DetailReceptionIntrantProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<ReceptionProxy> reception;
	private ReceptionDataProvider receptionDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CommandeProxy> commande;
	private CommandeDataProvider commandeDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DetailCommandeIntrantProxy> detailCommande;
	private DetailCommandeIntrantDataProvider detailCommandeDataProvider;
	@UiField(provided = true)
	EntreeLotEditorNestedRow entreeLot;
	private EntreeLotDataProvider entreeLotDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public DetailReceptionIntrantEditor(EpicamRequestFactory factory,
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
	public DetailReceptionIntrantEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.detailReceptionIntrant_group_description());
		reception.setLabel(NLS.constants()
				.detailReceptionIntrant_field_reception());
		// hidden field
		reception.setVisible(false);
		commande.setLabel(NLS.constants()
				.detailReceptionIntrant_field_commande());
		// the value of commande affects the value of other fields
		commande.notifyChanges(requestFactory.getEventBus());
		// hidden field
		commande.setVisible(false);
		detailCommande.setLabel(NLS.constants()
				.detailReceptionIntrant_field_detailCommande());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  reception */
		receptionDataProvider = new ReceptionDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			reception = new ImogSingleRelationBox<ReceptionProxy>(
					receptionDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateReception()
					&& AccessManager.canEditReception())
				reception = new ImogSingleRelationBox<ReceptionProxy>(
						receptionDataProvider, EpicamRenderer.get());
			else
				reception = new ImogSingleRelationBox<ReceptionProxy>(false,
						receptionDataProvider, EpicamRenderer.get());
		}

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
		detailCommandeDataProvider = new DetailCommandeIntrantDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			detailCommande = new ImogSingleRelationBox<DetailCommandeIntrantProxy>(
					detailCommandeDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateDetailCommandeIntrant()
					&& AccessManager.canEditDetailCommandeIntrant())
				detailCommande = new ImogSingleRelationBox<DetailCommandeIntrantProxy>(
						detailCommandeDataProvider, EpicamRenderer.get());
			else
				detailCommande = new ImogSingleRelationBox<DetailCommandeIntrantProxy>(
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

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		/* Description section widgets */
		// readonly field
		reception.setEdited(false);
		commande.setEdited(isEdited);
		detailCommande.setEdited(isEdited);
		entreeLot.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadDetailReceptionIntrantDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditDetailReceptionIntrantDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(
			DetailReceptionIntrantRequest ctx) {
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
		entreeLot.computeVisibility(source, allValidation);

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

	/**
	 * Setter to inject a Reception value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setReception(ReceptionProxy value, boolean isLocked) {
		reception.setLocked(isLocked);
		reception.setValue(value);

	}

	/** Widget update for field reception */
	private void clearReceptionWidget() {
		reception.clear();
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
	 * Setter to inject a DetailCommandeIntrant value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDetailCommande(DetailCommandeIntrantProxy value,
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

		/* 'Information' button for field Reception */
		reception.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (reception.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					ReceptionFormPanel form = new ReceptionFormPanel(
							requestFactory, reception.getValue().getId(),
							relationPopup, "reception");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Reception */
		reception.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				ReceptionFormPanel form = new ReceptionFormPanel(
						requestFactory, null, relationPopup, "reception");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Reception is created or updated from the relation field Reception */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveReceptionEvent.TYPE, new SaveReceptionEvent.Handler() {
					@Override
					public void saveReception(ReceptionProxy value) {
						reception.setValue(value);
					}
					@Override
					public void saveReception(ReceptionProxy value,
							String initField) {
						if (initField.equals("reception"))
							reception.setValue(value, true);
					}
				}));

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
					DetailCommandeIntrantFormPanel form = new DetailCommandeIntrantFormPanel(
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
				DetailCommandeIntrantFormPanel form = new DetailCommandeIntrantFormPanel(
						requestFactory, null, relationPopup, "detailCommande");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a DetailCommandeIntrant is created or updated from the relation field DetailCommande */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveDetailCommandeIntrantEvent.TYPE,
				new SaveDetailCommandeIntrantEvent.Handler() {
					@Override
					public void saveDetailCommandeIntrant(
							DetailCommandeIntrantProxy value) {
						detailCommande.setValue(value);
					}
					@Override
					public void saveDetailCommandeIntrant(
							DetailCommandeIntrantProxy value, String initField) {
						if (initField.equals("detailCommande"))
							detailCommande.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the DetailReceptionIntrantProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public DetailReceptionIntrantProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the DetailReceptionIntrantProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(DetailReceptionIntrantProxy editedValue) {
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
	private void setAllLabelWith(String width) {

		/* Description field group */
		reception.setLabelWidth(width);
		commande.setLabelWidth(width);
		detailCommande.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		reception.setBoxWidth(width);
		commande.setBoxWidth(width);
		detailCommande.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<DetailReceptionIntrantProxy> delegate) {
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
