package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.*;

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

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
 * in an editor list
 * @author MEDES-IMPS
 */
public class DetailRavitaillementEditorNestedRow extends Composite
		implements
			Editor<DetailRavitaillementProxy>,
			HasEditorDelegate<DetailRavitaillementProxy>,
			HasEditorErrors<DetailRavitaillementProxy> {

	interface Binder
			extends
				UiBinder<Widget, DetailRavitaillementEditorNestedRow> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<DetailRavitaillementProxy> delegate;

	private boolean hideButtons = false;
	private int index = 0;
	private boolean isNewProxy = false;

	@UiField
	Image clearImage;

	@UiField(provided = true)
	SortieLotEditorNestedRow sortieLot;
	private SortieLotDataProvider sortieLotDataProvider;
	@UiField(provided = true)
	EntreeLotEditorNestedRow entreeLot;
	private EntreeLotDataProvider entreeLotDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public DetailRavitaillementEditorNestedRow(EpicamRequestFactory factory,
			boolean hideButtons) {

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
	public DetailRavitaillementEditorNestedRow(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	public void properties() {

		//sortieLot.setLabel(NLS.constants().detailRavitaillement_field_sortieLot(), HasHorizontalAlignment.ALIGN_RIGHT);
		//entreeLot.setLabel(NLS.constants().detailRavitaillement_field_entreeLot(), HasHorizontalAlignment.ALIGN_RIGHT);

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	public void setRelationFields() {

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

		clearImage.setVisible(isEdited);

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		sortieLot.setEdited(isEdited);
		entreeLot.setEdited(isEdited);
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadDetailRavitaillementSortie())
			sortieLot.setVisible(false);

		if (!AccessManager.canReadDetailRavitaillementEntree())
			entreeLot.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	public void setFieldEditAccess() {

		if (!AccessManager.canEditDetailRavitaillementSortie())
			sortieLot.setVisible(false);

		if (!AccessManager.canEditDetailRavitaillementEntree())
			entreeLot.setVisible(false);

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

	public void setDeleteClickHandler(ClickHandler handler) {
		//registrations.add(clearImage.addClickHandler(handler));
		clearImage.addClickHandler(handler);
	}

	public void setCDTEntrant(CentreDiagTraitProxy value, boolean lock) {
		entreeLot.setCDT(value, lock);
	}

	public void setCDTSortant(CentreDiagTraitProxy value, boolean lock) {
		sortieLot.setCDT(value, lock);
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

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

		// sortieLot nested form shall be validated
		sortieLot.validateFields();
		// entreeLot nested form shall be validated
		entreeLot.validateFields();
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
