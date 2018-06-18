package org.imogene.epicam.client.ui.filter ; public class PriseMedicamentRegimeFilterPanel extends ImogFilterPanel { private TextBox medicament_designationBox ;  private ImogFilterBox medicament_designationFilterBox ;  private ImogBooleanAsRadio deletedEntityBox ;  private ImogFilterBox deletedEntityBoxFilterBox ;  public PriseMedicamentRegimeFilterPanel() { super() ;  setFieldReadAccess() ;  }  @Override public void resetFilterWidgets() { medicament_designationBox.setValue(null) ;  deletedEntityBox.setValue(false) ;  }  @Override public void createFilterWidgets() { medicament_designationBox = new TextBox() ;  medicament_designationFilterBox = new ImogFilterBox( medicament_designationBox) ;  medicament_designationFilterBox.setFilterLabel(NLS.constants() .medicament_field_designation()) ;  addTableFilter(medicament_designationFilterBox) ;  deletedEntityBox = new ImogBooleanAsRadio() ;  deletedEntityBox.isStrict(true) ;  deletedEntityBox.setEnabled(true) ;  deletedEntityBox.setValue(false) ;  deletedEntityBoxFilterBox = new ImogFilterBox(deletedEntityBox) ;  deletedEntityBoxFilterBox.setFilterLabel(BaseNLS.constants() .entity_field_deleted()) ;  addTableFilter(deletedEntityBoxFilterBox) ;  }  @Override public List<FilterCriteria> getFilterCriteria() { String locale = NLS.constants().locale() ;  List<FilterCriteria> criteria = new ArrayList<FilterCriteria>() ;  FilterCriteria medicament_designationCrit = new FilterCriteria() ;  medicament_designationCrit.setField("medicament.designation") ;  medicament_designationCrit.setFieldDisplayName(NLS.constants() .medicament_field_designation()) ;  medicament_designationCrit .setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS) ;  medicament_designationCrit.setValue(medicament_designationBox .getValue()) ;  medicament_designationCrit .setValueDisplayName(medicament_designationBox.getValue()) ;  criteria.add(medicament_designationCrit) ;  FilterCriteria deletedEntityCrit = new FilterCriteria() ;  deletedEntityCrit.setField("deleted") ;  deletedEntityCrit.setFieldDisplayName(BaseNLS.constants() .entity_field_deleted()) ;  if (deletedEntityBox.getValue()) { deletedEntityCrit .setOperation(CriteriaConstants.OPERATOR_ISNOTNULL) ;  deletedEntityCrit.setValue("true") ;  deletedEntityCrit.setValueDisplayName(BaseNLS.constants() .boolean_true()) ;  }  else { deletedEntityCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL) ;  deletedEntityCrit.setValue("false") ;  deletedEntityCrit.setValueDisplayName(BaseNLS.constants() .boolean_false()) ;  }  criteria.add(deletedEntityCrit) ;  return criteria ;  }  * Configures the visibility of the fields  * in view mode depending on the user privileges public void setFieldReadAccess() { if (!AccessManager.canReadMedicamentDescription()) medicament_designationFilterBox.setVisible(false) ;  if (!AccessManager.isAdmin()) deletedEntityBoxFilterBox.setVisible(false) ;  } }