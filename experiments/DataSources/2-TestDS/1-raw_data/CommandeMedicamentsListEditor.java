package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.CommandeRequest;

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
 * Editor that provides the UI components to manage the list of DetailCommandeMedicamentEditorNestedRow
 * in the Commande editor
 * @author MEDES-IMPS
 */
public class CommandeMedicamentsListEditor extends Composite
		implements
			IsEditor<ListEditor<DetailCommandeMedicamentProxy, DetailCommandeMedicamentEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, CommandeMedicamentsListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private DetailCommandeMedicamentListEditorSource editorSource;
	private ListEditor<DetailCommandeMedicamentProxy, DetailCommandeMedicamentEditorNestedRow> editor;
	//private ListEditor<DetailCommandeMedicamentProxy, DetailCommandeMedicamentEditorNestedForm> editor;
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
	Label medicamentLabel;
	@UiField
	@Ignore
	Label quantiteRequiseLabel;
	@UiField
	@Ignore
	Label quantiteEnStockLabel;

	public CommandeMedicamentsListEditor(EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new DetailCommandeMedicamentListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection.setGroupTitle(NLS.constants().commande_field_medicaments());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		medicamentLabel.setText(NLS.constants()
				.detailCommandeMedicament_field_medicament());
		quantiteRequiseLabel.setText(NLS.constants()
				.detailCommandeMedicament_field_quantiteRequise());
		quantiteEnStockLabel.setText(NLS.constants()
				.detailCommandeMedicament_field_quantiteEnStock());

	}

	/**
	 * Remove the DetailCommandeMedicament at the specified index
	 * @param index of the DetailCommandeMedicament
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the DetailCommandeMedicament at the specified index
	 * @param index of the DetailCommandeMedicament
	 */
	private DetailCommandeMedicamentProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<DetailCommandeMedicamentProxy, DetailCommandeMedicamentEditorNestedRow> asEditor() {
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
		DetailCommandeMedicamentProxy newDetailCommandeMedicament = request
				.create(DetailCommandeMedicamentProxy.class);
		newDetailCommandeMedicament.setId(ImogKeyGenerator
				.generateKeyId("DET_CMD_MED"));
		newDetailCommandeMedicament.setVersion(0);
		//request.saveMedicaments(newDetailCommandeMedicament, true);

		addValue(newDetailCommandeMedicament, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(DetailCommandeMedicamentProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<DetailCommandeMedicamentProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<DetailCommandeMedicamentEditorNestedRow> editors = editor
					.getEditors();
			DetailCommandeMedicamentEditorNestedRow subEditor = editors
					.get(editors.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
		}
	}

	public void up(DetailCommandeMedicamentEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(DetailCommandeMedicamentEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			DetailCommandeMedicamentEditorNestedRow subEditor = (DetailCommandeMedicamentEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<DetailCommandeMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeMedicamentEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<DetailCommandeMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeMedicamentEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<DetailCommandeMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeMedicamentEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<DetailCommandeMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeMedicamentEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class DetailCommandeMedicamentListEditorSource
			extends
				EditorSource<DetailCommandeMedicamentEditorNestedRow> {

		@Override
		public DetailCommandeMedicamentEditorNestedRow create(int index) {

			final DetailCommandeMedicamentEditorNestedRow subEditor = new DetailCommandeMedicamentEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						DetailCommandeMedicamentProxy proxy = getProxy(subEditor
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
		public void dispose(DetailCommandeMedicamentEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(DetailCommandeMedicamentEditorNestedRow subEditor,
				int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
