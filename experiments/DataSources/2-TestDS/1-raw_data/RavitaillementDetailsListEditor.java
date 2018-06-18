package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.RavitaillementRequest;

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
 * Editor that provides the UI components to manage the list of DetailRavitaillementEditorNestedRow
 * in the Ravitaillement editor
 * @author MEDES-IMPS
 */
public class RavitaillementDetailsListEditor extends Composite
		implements
			IsEditor<ListEditor<DetailRavitaillementProxy, DetailRavitaillementEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, RavitaillementDetailsListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private DetailRavitaillementListEditorSource editorSource;
	private ListEditor<DetailRavitaillementProxy, DetailRavitaillementEditorNestedRow> editor;
	//private ListEditor<DetailRavitaillementProxy, DetailRavitaillementEditorNestedForm> editor;
	private ImogEntityRequest request;
	private CentreDiagTraitProxy currentCdtEntrant;
	private CentreDiagTraitProxy currentCdtSortant;

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
	Label sortieLotLabel;
	@UiField
	@Ignore
	Label sortieLotQuantiteLabel;
	@UiField
	@Ignore
	Label entreeLotLabel;
	@UiField
	@Ignore
	Label entreeLotQuantiteLabel;

	public RavitaillementDetailsListEditor(EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new DetailRavitaillementListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection.setGroupTitle(NLS.constants()
				.ravitaillement_field_details());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		sortieLotLabel.setText(NLS.constants()
				.detailRavitaillement_field_sortieLot());
		sortieLotQuantiteLabel.setText(NLS.constants()
				.sortieLot_field_quantite());
		entreeLotLabel.setText(NLS.constants()
				.detailRavitaillement_field_entreeLot());
		entreeLotQuantiteLabel.setText(NLS.constants()
				.entreeLot_field_quantite());

	}

	/**
	 * Remove the DetailRavitaillement at the specified index
	 * @param index of the DetailRavitaillement
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the DetailRavitaillement at the specified index
	 * @param index of the DetailRavitaillement
	 */
	private DetailRavitaillementProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<DetailRavitaillementProxy, DetailRavitaillementEditorNestedRow> asEditor() {
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
		DetailRavitaillementProxy newDetailRavitaillement = request
				.create(DetailRavitaillementProxy.class);
		newDetailRavitaillement
				.setId(ImogKeyGenerator.generateKeyId("DET_RAV"));
		newDetailRavitaillement.setVersion(0);
		//create an instance of SortieLot in editor 
		SortieLotProxy newSortieLot = request.create(SortieLotProxy.class);
		newSortieLot.setId(ImogKeyGenerator.generateKeyId("SORT"));

		newDetailRavitaillement.setSortieLot(newSortieLot);
		//create an instance of EntreeLot in editor 
		EntreeLotProxy newEntreeLot = request.create(EntreeLotProxy.class);
		newEntreeLot.setId(ImogKeyGenerator.generateKeyId("ENTR"));

		newDetailRavitaillement.setEntreeLot(newEntreeLot);
		//request.saveDetails(newDetailRavitaillement, true);

		addValue(newDetailRavitaillement, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(DetailRavitaillementProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<DetailRavitaillementProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<DetailRavitaillementEditorNestedRow> editors = editor
					.getEditors();
			DetailRavitaillementEditorNestedRow subEditor = editors.get(editors
					.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
			subEditor.setCDTEntrant(currentCdtEntrant, true);
			subEditor.setCDTSortant(currentCdtSortant, true);
		}
	}

	public void up(DetailRavitaillementEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(DetailRavitaillementEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			DetailRavitaillementEditorNestedRow subEditor = (DetailRavitaillementEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<DetailRavitaillementEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailRavitaillementEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<DetailRavitaillementEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailRavitaillementEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	public void setCDTEntrant(CentreDiagTraitProxy value) {
		currentCdtEntrant = value;
	}

	public void updateCDTEntrant(CentreDiagTraitProxy value) {
		setCDTEntrant(value);
		List<DetailRavitaillementEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailRavitaillementEditorNestedRow subEditor : editors)
				subEditor.setCDTEntrant(value, true);
		}
	}

	public void setCDTSortant(CentreDiagTraitProxy value) {
		currentCdtSortant = value;
	}

	public void updateCDTSortant(CentreDiagTraitProxy value) {
		setCDTSortant(value);
		List<DetailRavitaillementEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailRavitaillementEditorNestedRow subEditor : editors)
				subEditor.setCDTSortant(value, true);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<DetailRavitaillementEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailRavitaillementEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<DetailRavitaillementEditorNestedRow> editors = editor.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailRavitaillementEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class DetailRavitaillementListEditorSource
			extends
				EditorSource<DetailRavitaillementEditorNestedRow> {

		@Override
		public DetailRavitaillementEditorNestedRow create(int index) {

			final DetailRavitaillementEditorNestedRow subEditor = new DetailRavitaillementEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						DetailRavitaillementProxy proxy = getProxy(subEditor
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
		public void dispose(DetailRavitaillementEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(DetailRavitaillementEditorNestedRow subEditor,
				int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
