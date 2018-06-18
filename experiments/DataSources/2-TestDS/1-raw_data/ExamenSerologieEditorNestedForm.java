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
import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;

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

/**
 * Editor that provides the UI components that allow a ExamenSerologieProxy to be viewed and edited
 * as a Nested Form
 * @author MEDES-IMPS
 */
public class ExamenSerologieEditorNestedForm extends Composite
		implements
			Editor<ExamenSerologieProxy>,
			HasEditorDelegate<ExamenSerologieProxy>,
			HasEditorErrors<ExamenSerologieProxy> {

	interface Binder extends UiBinder<Widget, ExamenSerologieEditorNestedForm> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<ExamenSerologieProxy> delegate;

	private boolean hideButtons = false;
	private int index = 0;
	private boolean isNewProxy = false;
	private boolean isClearImageActive = false;

	@UiField
	Image clearImage;

	@UiField
	@Ignore
	FieldGroupPanel examenSerologieSection;
	@UiField
	ImogDateBox dateTest;
	@UiField
	ImogSingleEnumBox nature;
	@UiField
	ImogIntegerBox resultatCD4;
	@UiField
	ImogSingleEnumBox resultatVIH;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public ExamenSerologieEditorNestedForm(EpicamRequestFactory factory,
			boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

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
	public ExamenSerologieEditorNestedForm(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	public void properties() {

		examenSerologieSection.setGroupTitle(NLS.constants()
				.examenSerologie_name());
		examenSerologieSection.setLabelFontSize("12px");

		dateTest.setLabel(NLS.constants().examenSerologie_field_dateTest());
		nature.setLabel(NLS.constants().examenSerologie_field_nature());
		nature.addItem(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH, NLS
				.constants().examenSerologie_nature_vIH_option());
		nature.addItem(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4, NLS
				.constants().examenSerologie_nature_cD4_option());
		// the value of nature affects the visibility of other fields
		nature.notifyChanges(requestFactory.getEventBus());
		resultatCD4.setLabel(NLS.constants()
				.examenSerologie_field_resultatCD4());
		// the visibility of resultatCD4 depends on the value of other fields
		resultatCD4.addStyleName("dependentVisibility");
		resultatVIH.setLabel(NLS.constants()
				.examenSerologie_field_resultatVIH());
		resultatVIH.addItem(
				EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF, NLS
						.constants()
						.examenSerologie_resultatVIH_positif_option());
		resultatVIH.addItem(
				EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF, NLS
						.constants()
						.examenSerologie_resultatVIH_negatif_option());
		// the visibility of resultatVIH depends on the value of other fields
		resultatVIH.addStyleName("dependentVisibility");
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

		dateTest.setEdited(isEdited);
		nature.setEdited(isEdited);
		resultatCD4.setEdited(isEdited);
		resultatVIH.setEdited(isEdited);
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadExamenSerologieDescription())
			dateTest.setVisible(false);

		if (!AccessManager.canReadExamenSerologieDescription())
			nature.setVisible(false);

		if (!AccessManager.canReadExamenSerologieDescription())
			resultatCD4.setVisible(false);

		if (!AccessManager.canReadExamenSerologieDescription())
			resultatVIH.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	public void setFieldEditAccess() {

		if (!AccessManager.canEditExamenSerologieDescription())
			dateTest.setVisible(false);

		if (!AccessManager.canEditExamenSerologieDescription())
			nature.setVisible(false);

		if (!AccessManager.canEditExamenSerologieDescription())
			resultatCD4.setVisible(false);

		if (!AccessManager.canEditExamenSerologieDescription())
			resultatVIH.setVisible(false);

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

					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		// the visibility of field resultatCD4 depends on the value of other fields
		if (allValidation || source.equals(nature)) {
			if ((nature.getValue() != null && nature.getValue().matches("1"))) {
				resultatCD4.setVisible(true);
			} else {
				resultatCD4.setVisible(false);
			}
		}

		// the visibility of field resultatVIH depends on the value of other fields
		if (allValidation || source.equals(nature)) {
			if ((nature.getValue() != null && nature.getValue().matches("0"))) {
				resultatVIH.setVisible(true);
			} else {
				resultatVIH.setVisible(false);
			}
		}

	}

	public void setDeleteClickHandler(ClickHandler handler) {
		//registrations.add(clearImage.addClickHandler(handler));
		clearImage.addClickHandler(handler);
		isClearImageActive = true;
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

		// dateTest is a required field
		if (dateTest.getValueWithoutParseException() == null
				&& dateTest.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateTest");
		// nature is a required field
		if (nature.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nature");

	}

	/**
	 */
	public void updateexamenSerologieSectionLabel(String label) {
		examenSerologieSection.setGroupTitle(label);
	}

	@Override
	public void setDelegate(EditorDelegate<ExamenSerologieProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> dateTestFieldErrors = new ArrayList<EditorError>();
			List<EditorError> natureFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("dateTest"))
						dateTestFieldErrors.add(error);
					if (field.equals("nature"))
						natureFieldErrors.add(error);
				}
			}
			if (dateTestFieldErrors.size() > 0)
				dateTest.showErrors(dateTestFieldErrors);
			if (natureFieldErrors.size() > 0)
				nature.showErrors(natureFieldErrors);
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
		setFieldValueChangeHandler();
		super.onLoad();
	}
}
