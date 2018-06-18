package org.imogene.epicam.client.ui.workflow.panel;

import org.imogene.epicam.client.EpicamIconConstants;
import org.imogene.epicam.client.event.list.ListOutBoxEvent;
import org.imogene.epicam.client.ui.workflow.OutBoxEditorWorkflow;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.OutBoxProxy;
import org.imogene.epicam.shared.proxy.PatientProxy;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.client.ui.panel.WrapperPanel;
import org.imogene.web.client.event.GoHomeEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel that contains the OutBox Workflow
 * @author Medes-IMPS
 */
public class OutBoxFormPanel extends Composite {

	private static final Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, OutBoxFormPanel> {
	}

	@UiField(provided = true)
	WrapperPanel wrapperPanel;
	@UiField(provided = true)
	OutBoxEditorWorkflow editorWorkflow;

	/**
	 * Constructor
	 * @param requestFactory the application requestFactory
	 * @param entityId the id of the entity to be managed by the workflow. null if an entity is being created
	 * @param parent parent composite if the panel is contained in a RelationPopupPanel
	 * @param initField the field that initiated the display in a RelationPopupPanel
	 * @param returnToList true if after closing the wokflow, the application shall display the list of entities, false otherwise
	 */
	public OutBoxFormPanel(EpicamRequestFactory requestFactory,
			String entityId, RelationPopupPanel parent, String initField) {

		wrapperPanel = new WrapperPanel();
		wrapperPanel.setWidth("90%");
		Label titleContainer = wrapperPanel.getTitleLabel();

		if (entityId != null) {
			if (parent == null)
				editorWorkflow = new OutBoxEditorWorkflow(requestFactory,
						entityId, titleContainer);
			else {
				editorWorkflow = new OutBoxEditorWorkflow(requestFactory,
						entityId, titleContainer, parent, initField);
			}
		} else {
			if (parent == null) {
				editorWorkflow = new OutBoxEditorWorkflow(requestFactory,
						titleContainer);
			} else {
				editorWorkflow = new OutBoxEditorWorkflow(requestFactory,
						titleContainer, parent, initField);
			}
		}

		if (EpicamIconConstants.OUTBOX_ICON != null)
			wrapperPanel.setIcon(EpicamIconConstants.OUTBOX_ICON);

		if (editorWorkflow.getEditButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getEditButton());
		if (editorWorkflow.getCloseButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getCloseButton());
		if (editorWorkflow.getSaveButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getSaveButton());
		if (editorWorkflow.getCancelButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getCancelButton());

		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Constructor
	 * @param requestFactory the application requestFactory
	 * @param entityId the id of the entity to be managed by the workflow. null if an entity is being created	 
	 */
	public OutBoxFormPanel(EpicamRequestFactory requestFactory, String entityId) {
		this(requestFactory, entityId, null, null);
	}

	public void setCloseEvent(GwtEvent<?> closeEvent) {
		editorWorkflow.setCloseEvent(closeEvent);
	}

	/**
	 * Setter to inject a Patient value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPatient(PatientProxy value, boolean isLocked) {
		editorWorkflow.setPatient(value, isLocked);
	}

	/**
	 * Setter to inject a Patient value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setPatient(PatientProxy value) {
		setPatient(value, false);
	}

}