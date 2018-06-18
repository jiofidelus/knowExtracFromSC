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
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.epicam.shared.request.EntreeLotRequest;

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

import org.imogene.epicam.client.ui.util.CommonFieldUtil;

import org.imogene.epicam.client.ui.workflow.panel.CentreDiagTraitFormPanel;
import org.imogene.epicam.client.event.save.SaveCentreDiagTraitEvent;
import org.imogene.epicam.client.dataprovider.CentreDiagTraitDataProvider;
import org.imogene.epicam.client.ui.filter.CentreDiagTraitFilterPanel;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.epicam.client.ui.workflow.panel.LotFormPanel;
import org.imogene.epicam.client.event.save.SaveLotEvent;
import org.imogene.epicam.client.dataprovider.LotDataProvider;
import org.imogene.epicam.client.ui.filter.LotFilterPanel;
import org.imogene.epicam.shared.proxy.LotProxy;

/**
 * Editor that provides the UI components that allow a EntreeLotProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class EntreeLotEditor extends Composite
		implements
			Editor<EntreeLotProxy>,
			HasEditorDelegate<EntreeLotProxy>,
			HasEditorErrors<EntreeLotProxy> {

	interface Binder extends UiBinder<Widget, EntreeLotEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<EntreeLotProxy> delegate;

	private EntreeLotProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDT;
	private CentreDiagTraitDataProvider cDTDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<LotProxy> lot;
	private LotDataProvider lotDataProvider;
	@UiField
	ImogIntegerBox quantite;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public EntreeLotEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public EntreeLotEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.entreeLot_group_description());
		CDT.setLabel(NLS.constants().entreeLot_field_cDT());
		// the value of CDT affects the value of other fields
		CDT.notifyChanges(requestFactory.getEventBus());
		lot.setLabel(NLS.constants().entreeLot_field_lot());
		quantite.setLabel(NLS.constants().entreeLot_field_quantite());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  CDT */
		cDTDataProvider = new CentreDiagTraitDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(
					cDTDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCentreDiagTrait()
					&& AccessManager.canEditCentreDiagTrait())
				CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(
						cDTDataProvider, EpicamRenderer.get());
			else
				CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(false,
						cDTDataProvider, EpicamRenderer.get());
		}

		/* field  lot */
		lotDataProvider = new LotDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			lot = new ImogSingleRelationBox<LotProxy>(lotDataProvider,
					EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateLot() && AccessManager.canEditLot())
				lot = new ImogSingleRelationBox<LotProxy>(lotDataProvider,
						EpicamRenderer.get());
			else
				lot = new ImogSingleRelationBox<LotProxy>(false,
						lotDataProvider, EpicamRenderer.get());
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
		CDT.setEdited(isEdited);
		lot.setEdited(isEdited);
		quantite.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadEntreeLotDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditEntreeLotDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(EntreeLotRequest ctx) {
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

						/* Lot list content depends on the value of field CDT */
						if (field.equals(CDT)) {
							clearLotWidget();
							getLotFilteredByCDT(CDT.getValue());

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
	 * Filters the content of the RelationField Lot by the value of 
	 * the RelationField CDT
	 * @param cDT the value of 
	 * the RelationField CDT 
	 */
	public void getLotFilteredByCDT(CentreDiagTraitProxy pCDT) {

		if (pCDT != null) {
			if (!hideButtons)
				lot.hideButtons(false);
			lotDataProvider.setFilterCriteria(pCDT.getId(), "CDT.id");
		} else {
			lot.hideButtons(true);
			lotDataProvider.setFilterCriteria(null);
		}
	}

	/**
	 * Setter to inject a CentreDiagTrait value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDT(CentreDiagTraitProxy value, boolean isLocked) {
		CDT.setLocked(isLocked);
		CDT.setValue(value);

		// Field Lot depends on the value of field cDT
		clearLotWidget();
		getLotFilteredByCDT(value);
	}

	/** Widget update for field CDT */
	private void clearCDTWidget() {
		CDT.clear();
	}

	/**
	 * Setter to inject a Lot value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setLot(LotProxy value, boolean isLocked) {
		lot.setLocked(isLocked);
		lot.setValue(value);

	}

	/** Widget update for field lot */
	private void clearLotWidget() {
		lot.clear();

	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field CDT */
		CDT.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (CDT.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
							requestFactory, CDT.getValue().getId(),
							relationPopup, "CDT");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CDT */
		CDT.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
						requestFactory, null, relationPopup, "CDT");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CentreDiagTrait is created or updated from the relation field CDT */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCentreDiagTraitEvent.TYPE,
				new SaveCentreDiagTraitEvent.Handler() {
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value) {
						CDT.setValue(value);
					}
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value,
							String initField) {
						if (initField.equals("CDT"))
							CDT.setValue(value, true);
					}
				}));

		/* 'Information' button for field Lot */
		lot.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (lot.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					LotFormPanel form = new LotFormPanel(requestFactory, lot
							.getValue().getId(), relationPopup, "lot");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Lot */
		lot.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				LotFormPanel form = new LotFormPanel(requestFactory, null,
						relationPopup, "lot");
				/* common fields */

				CommonFieldUtil commonFieldUtil = CommonFieldUtil.get();
				if (commonFieldUtil.getRegion() != null)
					form.setRegion(commonFieldUtil.getRegion(), true);
				if (commonFieldUtil.getDistrict() != null)
					form.setDistrictSante(commonFieldUtil.getDistrict(), true);
				if (commonFieldUtil.getCdt() != null)
					form.setCDT(commonFieldUtil.getCdt(), true);

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Lot is created or updated from the relation field Lot */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveLotEvent.TYPE, new SaveLotEvent.Handler() {
					@Override
					public void saveLot(LotProxy value) {
						lot.setValue(value);
					}
					@Override
					public void saveLot(LotProxy value, String initField) {
						if (initField.equals("lot"))
							lot.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the EntreeLotProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public EntreeLotProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the EntreeLotProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(EntreeLotProxy editedValue) {
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

		// cDT is a required field
		if (CDT.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cDT");
		// lot is a required field
		if (lot.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"lot");
		// quantite is a required field
		if (quantite.getValueWithoutParseException() == null
				&& quantite.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"quantite");
		// quantite shall be superior or equal to '0'
		if (quantite.getValueWithoutParseException() != null
				&& !(quantite.getValueWithoutParseException() >= 0))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().entreeLot_field_quantite_min()),
					null, "quantite");

	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		CDT.setLabelWidth(width);
		lot.setLabelWidth(width);
		quantite.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		CDT.setBoxWidth(width);
		lot.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<EntreeLotProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> cDTFieldErrors = new ArrayList<EditorError>();
			List<EditorError> lotFieldErrors = new ArrayList<EditorError>();
			List<EditorError> quantiteFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("cDT"))
						cDTFieldErrors.add(error);
					if (field.equals("lot"))
						lotFieldErrors.add(error);
					if (field.equals("quantite"))
						quantiteFieldErrors.add(error);

				}
			}
			if (cDTFieldErrors.size() > 0)
				CDT.showErrors(cDTFieldErrors);
			if (lotFieldErrors.size() > 0)
				lot.showErrors(lotFieldErrors);
			if (quantiteFieldErrors.size() > 0)
				quantite.showErrors(quantiteFieldErrors);
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
