package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateExamenBiologiqueEvent;
import org.imogene.epicam.client.event.list.ListExamenBiologiqueEvent;
import org.imogene.epicam.client.event.view.ViewExamenBiologiqueEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.ExamenBiologiqueFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.ExamenBiologiqueProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.ExamenBiologiqueRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.event.SelectionChangedInTableEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.client.ui.table.ImogColumn;
import org.imogene.web.client.ui.table.ImogDynaTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.epicam.shared.constants.EpicamBirtConstants;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Composite that displays the list of ExamenBiologique entries
 * @author MEDES-IMPS
 */
public class ExamenBiologiqueDynaTable
		extends
			ImogDynaTable<ExamenBiologiqueProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public ExamenBiologiqueDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<ExamenBiologiqueProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new ExamenBiologiqueFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadExamenBiologiqueDescription()) {
			Column<ExamenBiologiqueProxy, String> patientColumn = new PatientColumn();
			patientColumn.setSortable(true);
			table.addColumn(patientColumn, NLS.constants()
					.examenBiologique_field_s_patient());
		}
		if (AccessManager.canReadExamenBiologiqueDescription()) {
			Column<ExamenBiologiqueProxy, String> dateColumn = new DateColumn();
			dateColumn.setSortable(true);
			table.addColumn(dateColumn, NLS.constants()
					.examenBiologique_field_s_date());
		}
		if (AccessManager.canReadExamenBiologiqueDescription()) {
			Column<ExamenBiologiqueProxy, String> poidsColumn = new PoidsColumn();
			poidsColumn.setSortable(true);
			table.addColumn(poidsColumn, NLS.constants()
					.examenBiologique_field_s_poids());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(ExamenBiologiqueProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/examenbiologique/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "modified";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return false;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateExamenBiologique()
				&& AccessManager.canEditExamenBiologique()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/examenbiologique/",
							true);
				}
			};
			return command;
		} else
			return null;
	}

	/**
	 * Creates the Delete action command for the entity
	 * @return the delete command
	 */
	public PushButton getDeleteButton() {

		if (AccessManager.canDeleteExamenBiologique()
				&& AccessManager.canEditExamenBiologique()) {
			deleteButton = new PushButton(BaseNLS.constants().button_delete());
			deleteButton.setStyleName(imogResources.imogStyle().imogButton());
			deleteButton.addStyleName("Dynatable-Button");
			deleteButton.setVisible(false);
			return deleteButton;
		}

		return null;
	}

	/**
	 * Creates the Handlers linked to the delete button
	 */
	private void setDeleteButtonHandlers() {

		if (AccessManager.canDeleteExamenBiologique()
				&& AccessManager.canEditExamenBiologique()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<ExamenBiologiqueProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().examenBiologique_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (ExamenBiologiqueProxy entity : selectedEntities) {
							if (count == 1 || i == count - 1)
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "' ?");
							else
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "', ");
							i = i + 1;
						}

						boolean toDelete = Window.confirm(msg.toString());
						if (toDelete) {

							Request<Void> deleteRequest = getExamenBiologiqueRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected ExamenBiologique entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListExamenBiologiqueEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the ExamenBiologique entries");
									super.onFailure(error);
								}
							});
						}
					}

				}
			}));

			// Selection changed handler	
			registrations.add(requestFactory.getEventBus().addHandler(
					SelectionChangedInTableEvent.TYPE,
					new SelectionChangedInTableEvent.Handler() {
						@Override
						public void noticeSelectionChange(int selectedItems) {
							if (selectedItems > 0)
								deleteButton.setVisible(true);
							else
								deleteButton.setVisible(false);
						}
					}));
		}
	}

	/**
	 * Creates the action command that enables to export the ExamenBiologique
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportExamenBiologique()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.EXAM_BIO_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=examenBiologique_csv" + "&"
							+ EpicamBirtConstants.REPORT_LOCALE + "="
							+ NLS.constants().locale() + "&"
							+ EpicamBirtConstants.REPORT_FORMAT + "="
							+ EpicamBirtConstants.CSV;

					if (beanDataProvider.getSearchCriterions() != null)
						url = url + getDataProviderCriteria();

					Window.open(url, "_blank", "");
				}
			};
			return command;

		} else
			return null;
	}

	private ExamenBiologiqueRequest getExamenBiologiqueRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.examenBiologiqueRequest();
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
		setDeleteButtonHandlers();
		super.onLoad();
	}

	/**
	 * --------------------- * Internal classes * ----------------------
	 */

	/**
	 * Column for field Patient
	 * @author MEDES-IMPS
	 */
	private class PatientColumn
			extends
				ImogColumn<ExamenBiologiqueProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public PatientColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenBiologiqueProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPatient() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getPatient());
			}
			return value;
		}

		public String getPropertyName() {
			return "patient";
		}
	}

	/**
	 * Column for field Date
	 * @author MEDES-IMPS
	 */
	private class DateColumn extends ImogColumn<ExamenBiologiqueProxy, String> {

		public DateColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenBiologiqueProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDate() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDate());
			}
			return value;
		}

		public String getPropertyName() {
			return "date";
		}
	}

	/**
	 * Column for field Poids
	 * @author MEDES-IMPS
	 */
	private class PoidsColumn extends ImogColumn<ExamenBiologiqueProxy, String> {

		public PoidsColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenBiologiqueProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPoids() == null)
					value = "";
				else
					value = object.getPoids().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "poids";
		}
	}

}
