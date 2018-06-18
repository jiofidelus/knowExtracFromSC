package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;

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
 * Editor that provides the UI components to manage the list of PriseMedicamenteuseEditorNestedRow
 * in the CasTuberculose editor
 * @author MEDES-IMPS
 */
public class CasTuberculosePriseMedicamenteusePhaseInitialeListEditor
		extends
			Composite
		implements
			IsEditor<ListEditor<PriseMedicamenteuseProxy, PriseMedicamenteuseEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, CasTuberculosePriseMedicamenteusePhaseInitialeListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private PriseMedicamenteuseListEditorSource editorSource;
	private ListEditor<PriseMedicamenteuseProxy, PriseMedicamenteuseEditorNestedRow> editor;
	//private ListEditor<PriseMedicamenteuseProxy, PriseMedicamenteuseEditorNestedForm> editor;
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
	Label dateEffectiveLabel;
	@UiField
	@Ignore
	Label priseLabel;
	@UiField
	@Ignore
	Label cotrimoxazoleLabel;

	public CasTuberculosePriseMedicamenteusePhaseInitialeListEditor(
			EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new PriseMedicamenteuseListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection.setGroupTitle(NLS.constants()
				.casTuberculose_field_priseMedicamenteusePhaseInitiale());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		dateEffectiveLabel.setText(NLS.constants()
				.priseMedicamenteuse_field_dateEffective());
		dateEffectiveLabel.setWidth("153px");
		priseLabel.setText(NLS.constants().priseMedicamenteuse_field_prise());
		cotrimoxazoleLabel.setText(NLS.constants()
				.priseMedicamenteuse_field_cotrimoxazole());

	}

	/**
	 * Remove the PriseMedicamenteuse at the specified index
	 * @param index of the PriseMedicamenteuse
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the PriseMedicamenteuse at the specified index
	 * @param index of the PriseMedicamenteuse
	 */
	private PriseMedicamenteuseProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<PriseMedicamenteuseProxy, PriseMedicamenteuseEditorNestedRow> asEditor() {
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
		PriseMedicamenteuseProxy newPriseMedicamenteuse = request
				.create(PriseMedicamenteuseProxy.class);
		newPriseMedicamenteuse.setId(ImogKeyGenerator
				.generateKeyId("PRIS_MED_INIT"));
		newPriseMedicamenteuse.setVersion(0);
		//request.savePriseMedicamenteusePhaseInitiale(newPriseMedicamenteuse, true);

		addValue(newPriseMedicamenteuse, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(PriseMedicamenteuseProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<PriseMedicamenteuseProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<PriseMedicamenteuseEditorNestedRow> editors = editor
					.getEditors();
			PriseMedicamenteuseEditorNestedRow subEditor = editors.get(editors
					.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
		}
	}

	public void up(PriseMedicamenteuseEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(PriseMedicamenteuseEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			PriseMedicamenteuseEditorNestedRow subEditor = (PriseMedicamenteuseEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<PriseMedicamenteuseEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (PriseMedicamenteuseEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<PriseMedicamenteuseEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (PriseMedicamenteuseEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<PriseMedicamenteuseEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (PriseMedicamenteuseEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<PriseMedicamenteuseEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (PriseMedicamenteuseEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class PriseMedicamenteuseListEditorSource
			extends
				EditorSource<PriseMedicamenteuseEditorNestedRow> {

		@Override
		public PriseMedicamenteuseEditorNestedRow create(int index) {

			final PriseMedicamenteuseEditorNestedRow subEditor = new PriseMedicamenteuseEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						PriseMedicamenteuseProxy proxy = getProxy(subEditor
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
		public void dispose(PriseMedicamenteuseEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(PriseMedicamenteuseEditorNestedRow subEditor,
				int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
