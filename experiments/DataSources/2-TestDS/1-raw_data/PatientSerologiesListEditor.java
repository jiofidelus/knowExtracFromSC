package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.PatientRequest;

import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.ImogField;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.util.ImogKeyGenerator;
import org.imogene.web.shared.proxy.GeoFieldProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Editor that provides the UI components to manage the list of ExamenSerologieEditorNestedRow
 * in the Patient editor
 * @author MEDES-IMPS
 */
public class PatientSerologiesListEditor extends Composite
		implements
			IsEditor<ListEditor<ExamenSerologieProxy, ExamenSerologieEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, PatientSerologiesListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private ExamenSerologieListEditorSource editorSource;
	private ListEditor<ExamenSerologieProxy, ExamenSerologieEditorNestedRow> editor;
	//private ListEditor<ExamenSerologieProxy, ExamenSerologieEditorNestedForm> editor;
	private ImogEntityRequest request;

	@UiField(provided = true)
	@Ignore
	FieldGroupPanel listSection;
	@UiField(provided = true)
	VerticalPanel listContainer;
	@UiField(provided = true)
	@com.google.gwt.editor.client.Editor.Ignore
	Image addItem;

	/* header row (field names) */
	@UiField
	@Ignore
	Label dateTestLabel;
	@UiField
	@Ignore
	Label natureLabel;
	@UiField
	@Ignore
	Label resultatCD4Label;
	@UiField
	@Ignore
	Label resultatVIHLabel;

	public PatientSerologiesListEditor(EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new ExamenSerologieListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection.setGroupTitle(NLS.constants().patient_field_serologies());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		dateTestLabel.setText(NLS.constants().examenSerologie_field_dateTest());
		dateTestLabel.setWidth("153px");
		natureLabel.setText(NLS.constants().examenSerologie_field_nature());
		resultatCD4Label.setText(NLS.constants()
				.examenSerologie_field_resultatCD4());

		dateTestLabel.setWidth("100px");
	}

	/**
	 * Remove the ExamenSerologie at the specified index
	 * @param index of the ExamenSerologie
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the ExamenSerologie at the specified index
	 * @param index of the ExamenSerologie
	 */
	private ExamenSerologieProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<ExamenSerologieProxy, ExamenSerologieEditorNestedRow> asEditor() {
		return editor;
	}

	@UiHandler("addItem")
	void onAddClick(ClickEvent event) {
		add();
	}

	/**
	 * Adds a new value to the editor list
	 * Prerequisite: Context must have been set through the SetRequestContext method
	 */
	private void add() {
		ExamenSerologieProxy newExamenSerologie = request
				.create(ExamenSerologieProxy.class);
		newExamenSerologie.setId(ImogKeyGenerator.generateKeyId("EXAM_SER"));
		newExamenSerologie.setVersion(0);
		//request.saveSerologies(newExamenSerologie, true);

		addValue(newExamenSerologie, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(ExamenSerologieProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<ExamenSerologieProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<ExamenSerologieEditorNestedRow> editors = editor.getEditors();
			ExamenSerologieEditorNestedRow subEditor = editors.get(editors
					.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
		}
	}

	public void up(ExamenSerologieEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(ExamenSerologieEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			ExamenSerologieEditorNestedRow subEditor = (ExamenSerologieEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<ExamenSerologieEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (ExamenSerologieEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<ExamenSerologieEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (ExamenSerologieEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<ExamenSerologieEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (ExamenSerologieEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<ExamenSerologieEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (ExamenSerologieEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class ExamenSerologieListEditorSource
			extends
				EditorSource<ExamenSerologieEditorNestedRow> {

		@Override
		public ExamenSerologieEditorNestedRow create(int index) {

			final ExamenSerologieEditorNestedRow subEditor = new ExamenSerologieEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						ExamenSerologieProxy proxy = getProxy(subEditor
								.getIndex());
						if (!subEditor.isNewProxy())
							request.delete(proxy);
						remove(subEditor.getIndex());
						updateIndex();
					}
				}
			});
			return subEditor;
		}

		@Override
		public void dispose(ExamenSerologieEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(ExamenSerologieEditorNestedRow subEditor, int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
