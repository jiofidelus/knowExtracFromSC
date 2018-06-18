package org.imogene.admin.client.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.imogene.admin.client.AdminRenderer;
import org.imogene.admin.client.dataprovider.CardEntityDataProvider;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.admin.shared.AdminRequestFactory;
import org.imogene.web.client.event.FieldValueChangeEvent;
import org.imogene.web.client.ui.field.ImogField;
import org.imogene.web.client.ui.field.ImogTextBox;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.field.relation.single.ImogSingleRelationBox;
import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.shared.proxy.CardEntityProxy;
import org.imogene.web.shared.proxy.FieldGroupProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Editor that provides the UI components that allow a FieldGroupProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class FieldGroupEditor extends Composite implements Editor<FieldGroupProxy>, HasEditorDelegate<FieldGroupProxy>, HasEditorErrors<FieldGroupProxy> {

	interface Binder extends UiBinder<Widget, FieldGroupEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final AdminRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<FieldGroupProxy> delegate;

	private FieldGroupProxy editedValue; // Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField
	ImogTextBox name;
	@UiField(provided = true)
	ImogSingleRelationBox<CardEntityProxy> entity;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public FieldGroupEditor(AdminRequestFactory factory, boolean hideButtons) {

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
	public FieldGroupEditor(AdminRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(AdminNLS.constants().fieldGroup_group_description());
		name.setLabel(AdminNLS.constants().fieldGroup_field_name());
		entity.setLabel(AdminNLS.constants().fieldGroup_field_entity());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field entity */
		CardEntityDataProvider entityDataProvider;
		entityDataProvider = new CardEntityDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			entity = new ImogSingleRelationBox<CardEntityProxy>(entityDataProvider, AdminRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (ProfileUtil.isAdmin())
				entity = new ImogSingleRelationBox<CardEntityProxy>(entityDataProvider, AdminRenderer.get());
			else
				entity = new ImogSingleRelationBox<CardEntityProxy>(false, entityDataProvider, AdminRenderer.get());
		}
		entity.hideButtonPanel(true);

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
		name.setEdited(isEdited);
		entity.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!ProfileUtil.isAdmin())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!ProfileUtil.isAdmin())
			descriptionSection.setVisible(false);

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

		registrations.add(requestFactory.getEventBus().addHandler(FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() {
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
	 * Setter to inject a CardEntity value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setEntity(CardEntityProxy value, boolean isLocked) {
		entity.setLocked(isLocked);
		entity.setValue(value);

	}

	/**
	 * Gets the FieldGroupProxy that is edited in the Workflow Not used by the editor Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public FieldGroupProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the FieldGroupProxy that is edited in the Workflow Not used by the editor Temporary storage used to transmit the proxy to related entities
	 * @param editedValue
	 */
	public void setEditedValue(FieldGroupProxy editedValue) {
		this.editedValue = editedValue;
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

	}

	@Override
	public void setDelegate(EditorDelegate<FieldGroupProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
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
