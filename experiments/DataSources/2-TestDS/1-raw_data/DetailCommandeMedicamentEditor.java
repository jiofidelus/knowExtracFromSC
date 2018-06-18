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
import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;
import org.imogene.epicam.shared.request.DetailCommandeMedicamentRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.CommandeFormPanel;
import org.imogene.epicam.client.event.save.SaveCommandeEvent;
import org.imogene.epicam.client.dataprovider.CommandeDataProvider;
import org.imogene.epicam.client.ui.filter.CommandeFilterPanel;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.client.ui.workflow.panel.MedicamentFormPanel;
import org.imogene.epicam.client.event.save.SaveMedicamentEvent;
import org.imogene.epicam.client.dataprovider.MedicamentDataProvider;
import org.imogene.epicam.client.ui.filter.MedicamentFilterPanel;
import org.imogene.epicam.shared.proxy.MedicamentProxy;

/**
 * Editor that provides the UI components that allow a DetailCommandeMedicamentProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class DetailCommandeMedicamentEditor extends Composite
		implements
			Editor<DetailCommandeMedicamentProxy>,
			HasEditorDelegate<DetailCommandeMedicamentProxy>,
			HasEditorErrors<DetailCommandeMedicamentProxy> {

	interface Binder extends UiBinder<Widget, DetailCommandeMedicamentEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<DetailCommandeMedicamentProxy> delegate;

	private DetailCommandeMedicamentProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CommandeProxy> commande;
	private CommandeDataProvider commandeDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<MedicamentProxy> medicament;
	private MedicamentDataProvider medicamentDataProvider;
	@UiField
	ImogIntegerBox quantiteRequise;
	@UiField
	ImogIntegerBox quantiteEnStock;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public DetailCommandeMedicamentEditor(EpicamRequestFactory factory,
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
	public DetailCommandeMedicamentEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.detailCommandeMedicament_group_description());
		commande.setLabel(NLS.constants()
				.detailCommandeMedicament_field_commande());
		// hidden field
		commande.setVisible(false);
		medicament.setLabel(NLS.constants()
				.detailCommandeMedicament_field_medicament());
		quantiteRequise.setLabel(NLS.constants()
				.detailCommandeMedicament_field_quantiteRequise());
		quantiteEnStock.setLabel(NLS.constants()
				.detailCommandeMedicament_field_quantiteEnStock());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

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

		/* field  medicament */
		medicamentDataProvider = new MedicamentDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			medicament = new ImogSingleRelationBox<MedicamentProxy>(
					medicamentDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateMedicament()
					&& AccessManager.canEditMedicament())
				medicament = new ImogSingleRelationBox<MedicamentProxy>(
						medicamentDataProvider, EpicamRenderer.get());
			else
				medicament = new ImogSingleRelationBox<MedicamentProxy>(false,
						medicamentDataProvider, EpicamRenderer.get());
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
		// readonly field
		commande.setEdited(false);
		medicament.setEdited(isEdited);
		quantiteRequise.setEdited(isEdited);
		// readonly field
		quantiteEnStock.setEdited(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadDetailCommandeMedicamentDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditDetailCommandeMedicamentDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(
			DetailCommandeMedicamentRequest ctx) {
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
	 * Setter to inject a Commande value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCommande(CommandeProxy value, boolean isLocked) {
		commande.setLocked(isLocked);
		commande.setValue(value);

	}

	/** Widget update for field commande */
	private void clearCommandeWidget() {
		commande.clear();
	}

	/**
	 * Setter to inject a Medicament value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setMedicament(MedicamentProxy value, boolean isLocked) {
		medicament.setLocked(isLocked);
		medicament.setValue(value);

	}

	/** Widget update for field medicament */
	private void clearMedicamentWidget() {
		medicament.clear();
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

		/* 'Information' button for field Medicament */
		medicament.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (medicament.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					MedicamentFormPanel form = new MedicamentFormPanel(
							requestFactory, medicament.getValue().getId(),
							relationPopup, "medicament");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Medicament */
		medicament.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				MedicamentFormPanel form = new MedicamentFormPanel(
						requestFactory, null, relationPopup, "medicament");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Medicament is created or updated from the relation field Medicament */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveMedicamentEvent.TYPE, new SaveMedicamentEvent.Handler() {
					@Override
					public void saveMedicament(MedicamentProxy value) {
						medicament.setValue(value);
					}
					@Override
					public void saveMedicament(MedicamentProxy value,
							String initField) {
						if (initField.equals("medicament"))
							medicament.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the DetailCommandeMedicamentProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public DetailCommandeMedicamentProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the DetailCommandeMedicamentProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(DetailCommandeMedicamentProxy editedValue) {
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

		// medicament is a required field
		if (medicament.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"medicament");
		// quantiteRequise is a required field
		if (quantiteRequise.getValueWithoutParseException() == null
				&& quantiteRequise.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"quantiteRequise");
		// quantiteRequise shall be superior or equal to '1'
		if (quantiteRequise.getValueWithoutParseException() != null
				&& !(quantiteRequise.getValueWithoutParseException() >= 1))
			delegate.recordError(
					BaseNLS.messages()
							.error_min_num(
									NLS.constants()
											.detailCommandeMedicament_field_quantiteRequise_min()),
					null, "quantiteRequise");

		// quantiteEnStock shall be superior or equal to '0'
		if (quantiteEnStock.getValueWithoutParseException() != null
				&& !(quantiteEnStock.getValueWithoutParseException() >= 0))
			delegate.recordError(
					BaseNLS.messages()
							.error_min_num(
									NLS.constants()
											.detailCommandeMedicament_field_quantiteEnStock_min()),
					null, "quantiteEnStock");

	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		commande.setLabelWidth(width);
		medicament.setLabelWidth(width);
		quantiteRequise.setLabelWidth(width);
		quantiteEnStock.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		commande.setBoxWidth(width);
		medicament.setBoxWidth(width);

	}

	@Override
	public void setDelegate(
			EditorDelegate<DetailCommandeMedicamentProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> medicamentFieldErrors = new ArrayList<EditorError>();
			List<EditorError> quantiteRequiseFieldErrors = new ArrayList<EditorError>();
			List<EditorError> quantiteEnStockFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("medicament"))
						medicamentFieldErrors.add(error);
					if (field.equals("quantiteRequise"))
						quantiteRequiseFieldErrors.add(error);
					if (field.equals("quantiteEnStock"))
						quantiteEnStockFieldErrors.add(error);

				}
			}
			if (medicamentFieldErrors.size() > 0)
				medicament.showErrors(medicamentFieldErrors);
			if (quantiteRequiseFieldErrors.size() > 0)
				quantiteRequise.showErrors(quantiteRequiseFieldErrors);
			if (quantiteEnStockFieldErrors.size() > 0)
				quantiteEnStock.showErrors(quantiteEnStockFieldErrors);
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