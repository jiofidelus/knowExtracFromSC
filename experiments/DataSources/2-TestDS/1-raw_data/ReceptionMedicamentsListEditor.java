package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.ReceptionRequest;

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
 * Editor that provides the UI components to manage the list of DetailReceptionMedicamentEditorNestedRow
 * in the Reception editor
 * @author MEDES-IMPS
 */
public class ReceptionMedicamentsListEditor extends Composite
		implements
			IsEditor<ListEditor<DetailReceptionMedicamentProxy, DetailReceptionMedicamentEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, ReceptionMedicamentsListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private DetailReceptionMedicamentListEditorSource editorSource;
	private ListEditor<DetailReceptionMedicamentProxy, DetailReceptionMedicamentEditorNestedRow> editor;
	//private ListEditor<DetailReceptionMedicamentProxy, DetailReceptionMedicamentEditorNestedForm> editor;
	private ImogEntityRequest request;
	private CommandeProxy currentCommande;
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
	Label detailCommandeLabel;
	@UiField
	@Ignore
	Label entreeLotLabel;
	@UiField
	@Ignore
	Label entreeLotQuantiteLabel;

	public ReceptionMedicamentsListEditor(EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new DetailReceptionMedicamentListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection
				.setGroupTitle(NLS.constants().reception_field_medicaments());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		detailCommandeLabel.setText(NLS.constants()
				.detailReceptionMedicament_field_detailCommande());
		entreeLotLabel.setText(NLS.constants()
				.detailReceptionMedicament_field_entreeLot());
		entreeLotQuantiteLabel.setText(NLS.constants()
				.entreeLot_field_quantite());

	}

	/**
	 * Remove the DetailReceptionMedicament at the specified index
	 * @param index of the DetailReceptionMedicament
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the DetailReceptionMedicament at the specified index
	 * @param index of the DetailReceptionMedicament
	 */
	private DetailReceptionMedicamentProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<DetailReceptionMedicamentProxy, DetailReceptionMedicamentEditorNestedRow> asEditor() {
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
		DetailReceptionMedicamentProxy newDetailReceptionMedicament = request
				.create(DetailReceptionMedicamentProxy.class);
		newDetailReceptionMedicament.setId(ImogKeyGenerator
				.generateKeyId("DET_REC_MED"));
		newDetailReceptionMedicament.setVersion(0);
		//create an instance of EntreeLot in editor 
		EntreeLotProxy newEntreeLot = request.create(EntreeLotProxy.class);
		newEntreeLot.setId(ImogKeyGenerator.generateKeyId("ENTR"));

		newDetailReceptionMedicament.setEntreeLot(newEntreeLot);
		//request.saveMedicaments(newDetailReceptionMedicament, true);

		newDetailReceptionMedicament.setCommande(currentCommande);

		addValue(newDetailReceptionMedicament, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(DetailReceptionMedicamentProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<DetailReceptionMedicamentProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<DetailReceptionMedicamentEditorNestedRow> editors = editor
					.getEditors();
			DetailReceptionMedicamentEditorNestedRow subEditor = editors
					.get(editors.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
			subEditor.setCommande(currentCommande, true);
			subEditor.setCDT(currentCdt, true);
		}
	}

	public void up(DetailReceptionMedicamentEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(DetailReceptionMedicamentEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			DetailReceptionMedicamentEditorNestedRow subEditor = (DetailReceptionMedicamentEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<DetailReceptionMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailReceptionMedicamentEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<DetailReceptionMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailReceptionMedicamentEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	public void setCommande(CommandeProxy value) {
		currentCommande = value;
	}

	public void updateCommande(CommandeProxy value) {
		setCommande(value);
		List<DetailReceptionMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailReceptionMedicamentEditorNestedRow subEditor : editors)
				subEditor.setCommande(value, true);
		}
	}

	public void setCDT(CentreDiagTraitProxy value) {
		currentCdt = value;
	}

	public void updateCDT(CentreDiagTraitProxy value) {
		setCDT(value);
		List<DetailReceptionMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailReceptionMedicamentEditorNestedRow subEditor : editors)
				subEditor.setCDT(value, true);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<DetailReceptionMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailReceptionMedicamentEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<DetailReceptionMedicamentEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailReceptionMedicamentEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class DetailReceptionMedicamentListEditorSource
			extends
				EditorSource<DetailReceptionMedicamentEditorNestedRow> {

		@Override
		public DetailReceptionMedicamentEditorNestedRow create(int index) {

			final DetailReceptionMedicamentEditorNestedRow subEditor = new DetailReceptionMedicamentEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						DetailReceptionMedicamentProxy proxy = getProxy(subEditor
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
		public void dispose(DetailReceptionMedicamentEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(
				DetailReceptionMedicamentEditorNestedRow subEditor, int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
