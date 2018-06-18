package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.InventaireRequest;

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
 * Editor that provides the UI components to manage the list of DetailInventaireEditorNestedRow
 * in the Inventaire editor
 * @author MEDES-IMPS
 */
public class InventaireDetailsListEditor extends Composite
		implements
			IsEditor<ListEditor<DetailInventaireProxy, DetailInventaireEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, InventaireDetailsListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private DetailInventaireListEditorSource editorSource;
	private ListEditor<DetailInventaireProxy, DetailInventaireEditorNestedRow> editor;
	//private ListEditor<DetailInventaireProxy, DetailInventaireEditorNestedForm> editor;
	private ImogEntityRequest request;
	private CentreDiagTraitProxy currentCdt;

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
	Label CDTLabel;
	@UiField
	@Ignore
	Label lotLabel;
	@UiField
	@Ignore
	Label quantiteReelleLabel;
	@UiField
	@Ignore
	Label quantiteTheoriqueLabel;

	public InventaireDetailsListEditor(EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new DetailInventaireListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection.setGroupTitle(NLS.constants().inventaire_field_details());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		lotLabel.setText(NLS.constants().detailInventaire_field_lot());
		quantiteReelleLabel.setText(NLS.constants()
				.detailInventaire_field_quantiteReelle());
		quantiteTheoriqueLabel.setText(NLS.constants()
				.detailInventaire_field_quantiteTheorique());

	}

	/**
	 * Remove the DetailInventaire at the specified index
	 * @param index of the DetailInventaire
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the DetailInventaire at the specified index
	 * @param index of the DetailInventaire
	 */
	private DetailInventaireProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<DetailInventaireProxy, DetailInventaireEditorNestedRow> asEditor() {
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
		DetailInventaireProxy newDetailInventaire = request
				.create(DetailInventaireProxy.class);
		newDetailInventaire.setId(ImogKeyGenerator.generateKeyId("DET_INV"));
		newDetailInventaire.setVersion(0);
		//request.saveDetails(newDetailInventaire, true);

		newDetailInventaire.setCDT(currentCdt);

		addValue(newDetailInventaire, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(DetailInventaireProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<DetailInventaireProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<DetailInventaireEditorNestedRow> editors = editor.getEditors();
			DetailInventaireEditorNestedRow subEditor = editors.get(editors
					.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
			subEditor.setCDT(currentCdt, true);
		}
	}

	public void up(DetailInventaireEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(DetailInventaireEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			DetailInventaireEditorNestedRow subEditor = (DetailInventaireEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<DetailInventaireEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailInventaireEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<DetailInventaireEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailInventaireEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	public void setCDT(CentreDiagTraitProxy value) {
		currentCdt = value;
	}

	public void updateCDT(CentreDiagTraitProxy value) {
		setCDT(value);
		List<DetailInventaireEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailInventaireEditorNestedRow subEditor : editors)
				subEditor.setCDT(value, true);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<DetailInventaireEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailInventaireEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<DetailInventaireEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailInventaireEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class DetailInventaireListEditorSource
			extends
				EditorSource<DetailInventaireEditorNestedRow> {

		@Override
		public DetailInventaireEditorNestedRow create(int index) {

			final DetailInventaireEditorNestedRow subEditor = new DetailInventaireEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						DetailInventaireProxy proxy = getProxy(subEditor
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
		public void dispose(DetailInventaireEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(DetailInventaireEditorNestedRow subEditor,
				int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
