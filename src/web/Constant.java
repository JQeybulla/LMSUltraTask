package web;

public class Constant {
    public static final String REQUIRES_AUTH = "requiresAuth.jsp";
    public static final String ACTION_LOGIN = "login";
    public static final String COMBO_CONTENT = "comboContent.jsp";
    public static final String ACTION_LOAD_MAIN_GRID = "actionLoadMainGrid.jsp";
    public static final String AUTHENTICATION_TOKEN = "authenticationToken.jsp";
    public static final String PAGE_LOGIN = "Login.jsp";
    public static final String PAGE_MAIN = "index.jsp";
    public static final String PAGE_MAIN_STUDENT = "student.jsp";

    public static final String USER = "user";
    public static final String USER_ALLOW = "userAllow";

    public static final String MESSAGE = "message";
    public static final String OPERATION_BUTTONS = "operationButtons";
    public static final String MODULS = "moduls";

    public static final String ACTION_SAVE_MAINDOCS = "saveMainDocs";
    public static final String ACTION_SAVE_PHOTO = "savePhoto";
    public static final String ACTION_DOWN_FILE = "opFile";
    public static final String ACTION_GET_PERSON_FILE = "loadPhoto";
    public static final String ATT_MMIS = "MMIS/";
    public static final String ATT_PHOTO = "PHOTO/";
    public static final String ATT_SCHOOL_FILE = "SCHOOLFILE/";
    public static final String ATT_STUDENT_FILE = "STUDENTFILE/";
    public static final String ATT_STAFF_FILE = "STAFFFILE/";

    public static final String ACTION_LOAD_DICT_LIST = "loadDicList";
    public static final String GRID_CONTENT = "grid_content";
    public static final String GRID_TEACHING_CONTENT = "grid_teaching_content";
    public static final String GRID_SPEC_CONTENT = "grid_spec_content";
    public static final String GRID_FAMILY_CONTENT = "grid_family_content";
    public static final String GRID_TOTAL_COUNT = "gridTotalCount";
    public static final String ACTION_SAVE_DICTIONARY = "saveDictionary";
    public static final String ACTION_SAVE_USER_HISTORY = "saveUserOperationHistory";

    public static final String ACTION_OPEN_DIALOG_FOR_STAFF_NEW = "openDialogForStaffNew";
    public static final String ACTION_OPEN_DIALOG_FOR_STUDENT_NEW = "openDialogForStudentNew";

    public static final String ACTION_OPEN_DIALOG_FOR_IDENTITY = "openDialogForIdentitySearch";

    public static final String ACTION_OPEN_DIALOG_FOR_STAFF_EDIT = "openDialogForStaffEdit";
    public static final String ACTION_OPEN_DIALOG_FOR_SCHOOL_EDIT = "openDialogForSchoolEdit";
    public static final String ACTION_OPEN_DIALOG_FOR_STUDENT_EDIT = "openDialogForStudentEdit";

    public static final String ACTION_OPEN_DIALOG_FOR_DELETE = "openDialogForDelete";
    public static final String ACTION_DELETE_FROM_MAIN_GRID = "deleteFromMainGrid";

    public static final String PERSON_ID = "person_id";
    public static final String TEACHER_ID = "teacherId";

    public static final String ACTION_GET_CHECK_CONTACT_SCH = "getContactChckSch";
    public static final String ACTION_SAVE_GROUP_HOUR="saveGroupHour";

    public static final String ACTION_APPROVE_STATUS="getApproveStatus";

    //school

    public static final String ACTION_GET_SCHOOL_COMMON_INFO = "getSchoolCommonInfoById";
    public static final String ACTION_SAVE_SCHOOL_COMMON_INFO = "saveSchoolCommonInfo";
    public static final String ACTION_LOAD_SCHOOL_GRID_INFO = "loadSchoolGridInfo";


    // struktur
    public static final String ACTION_LOAD_STRUKTUR = "loadStrukturList";
    public static final String ACTION_GET_STRUKTUR_ORG_INFO = "getStrukturFullInfo";
    public static final String ACTION_SAVE_STRUKTUR = "saveStrukturInfo"; // new and edit
    public static final String SES_ORG_INFO = "getOrgInfo";
    public static final String SES_PADDRESS = "personalAddress";
    public static final String SES_PCONTACTS = "personalContact";
    public static final String SES_SPADDRESS = "secondPopupPersonalAddress";
    public static final String SES_SPCONTACTS = "secondPopupPersonalContact";
    public static final String ACTION_SAVE_SCHOOL_ADDRESS_INFO = "saveSchoolAddressInfo";
    public static final String ACTION_SAVE_SCHOOL_CONTACT_INFO = "saveSchoolContactInfo";


    //search
    public static final String ACTION_SET_SEARCH_TEXT = "setData";
    public static final String ACTION_GET_PERSON_IDENTITY_SEARCH = "getPersonIdentitySearch";
    public static final String ACTION_GET_PERSON_IDENTITY_SEARCHUTIS = "getPersonIdentitySearchUTIS";
    public static final String ACTION_PIN_SERVICE = "PinService";
    public static final String ACTION_MIG_SERVICE = "MigService";
    public static final String ACTION_MET_SERVICE = "MetService";

    //USER
    public static final String ACTION_CHANGE_USER_PASSWORD = "changeUserPassword";
    public static final String ACTION_CHECK_USERNAME = "checkUser";
    public static final String ACTION_SAVE_USERS = "saveUsers";
    public static final String ACTION_CHECK_USER_ACCESS = "checkUserAccess";
    public static final String ACTION_LOAD_USERINFO = "loadUserInfo";
    public static final String ACTION_SET_SESSION = "setSession";
    public static final String SES_LOGINFO = "personOperationLog";


    public static final String GRID = "grid";
    public static final String REPORTMODULS = "reportModuls";
    public static final String ACTION_GET_GRID_CONTENT = "getGridContent";
    public static final String ACTION_GET_TEACHING_GRID_CONTENT = "getGridTeachingContent";
    public static final String ACTION_GET_FAMILY_GRID_CONTENT = "getGridFamilyContent";
    public static final String ACTION_GET_SPEC_GRID_CONTENT = "getGridSpecContent";
    public static final String ACTION_GET_CONTACT_GRID_CONTENT = "getGridContactContent";
    public static final String ACTION_GET_DYNAMIC_GRID_CONTENT = "getDynamicGridContent";
    public static final String RAPORT_STATIC_SERVLET = "raportStaticServlet"; // RAPORT_STATIC_SERVLET  by_vusal


    public static final String OPERATION_BUTTON_LIST = "operationButtonList";
    public static final String GET_OPERATION_BUTTONS1 = "getOperationButtons1";
    public static final String GET_OPERATION_BUTTONS2 = "getOperationButtons2";
    public static final String GET_OPERATION_BUTTONS3 = "getOperationButtons3";
    public static final String ACTION_GET_USER_GROUP_OPERATIONS = "getUserGroupOperations";
    public static final String USER_GROUP = "userGroup";


    public static final String INFO_ATTRIBUTE = "infoattribute";

    //dialoglar
    public static final String ACTION_OPEN_DIALOG_FOR_USER_NEW = "openDialogForUserNew";
    public static final String ACTION_OPEN_DIALOG_FOR_USER_EDIT = "openDialogForUserEdit";

    public static final String ACTION_GET_SECURITY_GRID = "getSecurityGrid";


    public static final String ACTION_DOWNLOAD_FILE = "downloadFile";
    public static final String ACTION_REPORT_DOWNLOAD = "downloadReportFile";

    //person
    public static final String ACTION_GET_PERSON_COMMON_INFO = "getPersonCommonInfoById";
    public static final String ACTION_GET_STUDENT_COMMON_INFO = "getStudentCommonInfoById";
    public static final String ACTION_GET_COUNT_STUD_CLASS = "getCountStudClass";

    public static final String ACTION_GET_STUDENT_RIGHT_PANEL_INFO = "getStudentRightPanelInfoById";
    public static final String ACTION_CHECK_TEACHER_CURRENT_WORK = "checkTeacherCurrentWork";
    public static final String ACTION_UPDATE_PERON_INFO_BY_IAMAS = "updatePersonInfoByIamas";


    public static final String ACTION_SAVE_STUFF_COMMON_INFO = "saveStuffCommonInfo";
    public static final String ACTION_SAVE_STUFF_AS_NEW_PERSON = "saveStuffAsNewPerson";
    public static final String ACTION_SAVE_STUDENT_AS_NEW_PERSON = "saveStudentAsNewPerson";
    public static final String ACTION_SAVE_STUDENT_COMMON_INFO = "saveStudentCommonInfo";


    //tables
    public static final String TABLE_SEC_USERS = " MMIS_SECURITY.SEC_USERS ";
    public static final String TABLE_SEC_USERS_ACCESS = " MMIS_SECURITY.SEC_USER_ACCESS ";
    public static final String TABLE_SEC_USERS_PASSCHANGE = " MMIS_SECURITY.SEC_USER_PASSWORDSTATUS ";
    public static final String TABLE_COM_DICTIONARY = " MMIS_COMMON.COM_DICTIONARY ";
    public static final String TABLE_HR_ORG_POSITION = " MMIS_HR.HR_ORG_POSITION ";
    public static final String TABLE_COM_PERSONS = " MMIS_COMMON.COM_PERSONS ";
    public static final String TABLE_HR_EMPLOYEES = " MMIS_HR.HR_EMPLOYEES ";

    public static final String TABLE_COM_PERSON_DOCUMENT = " MMIS_COMMON.COM_PERSON_DOCUMENTS ";
    public static final String TABLE_COM_PERSON = " MMIS_COMMON.COM_PERSONS ";
    public static final String TABLE_COM_PERSON_ADDRESS = " MMIS_COMMON.COM_PERSON_ADDRESS ";
    public static final String TABLE_COM_PERSON_CONTACTS = " MMIS_COMMON.COM_PERSON_CONTACTS ";
    public static final String TABLE_COM_PERSON_PHOTO = " MMIS_COMMON.COM_PERSON_PHOTO ";
    public static final String TABLE_COM_PERSON_ADDITIONAL = " MMIS_COMMON.COM_PERSON_ADDITIONAL ";


    // MEKTEB
    public static final String TABLE_SCH_MAIN = " MMIS_SCHOOLS.SCH_MAIN ";
    public static final String TABLE_SCH_OTHER_PLACE = " MMIS_SCHOOLS.SCH_OTHER_PLACE ";
    public static final String TABLE_SCH_ADDITIONAL = " MMIS_SCHOOLS.SCH_ADDITIONAL ";
    public static final String TABLE_SCH_EDU_LANG = " MMIS_SCHOOLS.SCH_EDU_LANG ";
    public static final String TABLE_SCH_FOREIGN_LANG = " MMIS_SCHOOLS.SCH_FOREIGN_LANG ";
    public static final String TABLE_SCH_BUILDING = " MMIS_SCHOOLS.SCH_BUILDING ";
    public static final String TABLE_SCH_LIBRARY = " MMIS_SCHOOLS.SCH_LIBRARY ";
    public static final String TABLE_SCH_COMMUNAL = " MMIS_SCHOOLS.SCH_COMMUNAL ";
    public static final String TABLE_SCH_BUFCANTEEN = " MMIS_SCHOOLS.SCH_CANTEEN ";
    public static final String TABLE_SCH_MATERIAL_BASE = " MMIS_SCHOOLS.SCH_MATERIAL_BASE ";
    public static final String TABLE_SCH_SPORT = " MMIS_SCHOOLS.SCH_SPORT ";
    public static final String TABLE_SCH_IMMOVABLE_PROPERTY = " MMIS_SCHOOLS.SCH_IMMOVABLE_PROPERTY ";
    public static final String TABLE_SCH_TRANSPORT = " MMIS_SCHOOLS.SCH_TRANSPORT ";
    public static final String TABLE_SCH_EXCURSION = " MMIS_SCHOOLS.SCH_EXCURSION ";
    public static final String TABLE_SCH_EVENTS = " MMIS_SCHOOLS.SCH_EVENTS ";
    public static final String TABLE_SCH_INSTRUM_NAMES = " MMIS_SCHOOLS.SCH_MUSICAL_INSTRUMENTS ";
    public static final String TABLE_SCH_TECHNICAL_TOOLS = " MMIS_SCHOOLS.SCH_TECHNICAL_TOOLS ";
    public static final String TABLE_SCH_FINANCE = " MMIS_SCHOOLS.SCH_FINANCE ";
    public static final String TABLE_SCH_REPAIR = " MMIS_SCHOOLS.SCH_REPAIR ";
    public static final String TABLE_SCH_EDU_DOC = " MMIS_SCHOOLS.SCH_EDU_DOC ";
    public static final String TABLE_SCH_CUR_STATUS = " MMIS_SCHOOLS.SCH_CURRENT_STATUS ";
    public static final String TABLE_SCH_ATTENDANCE = " MMIS_SCHOOLS.SCH_ATTENDANCE ";
    public static final String TABLE_ORG_TREE_ADDRESS = " MMIS_ORGANIZATION.ORG_TREE_INF_ADDRESS ";
    public static final String TABLE_ORG_TREE_CONTACT = " MMIS_ORGANIZATION.ORG_TREE_INF_CONTACT ";
    public static final String ACTION_COUNT_STAFF_STUD_CLASS = "countStaffStudClass";
    public static final String ACTION_SAVE_SCHOOL_CLOSE = "saveSchoolClose";
    public static final String ACTION_SAVE_SCHOOL_OPEN = "saveSchoolOpen";
    public static final String ACTION_UPDATE_SCHOOL_HISTORY = "updateSchoolHistory";
    public static final String ACTION_SAVE_EDU_DOC="addStudEduDocType";
    public static final String TABLE_COM_DOCS = " MMIS_COMMON.COM_DOCS ";
    public static final String TABLE_COM_ADDRESS = " MMIS_COMMON.COM_ADDRESS ";
    public static final String TABLE_SCH_EDUCLASS = " MMIS_SCHOOLS.SCH_EDU_CLASS ";
    public static final String TABLE_SCH_GROUP_HOUR = " MMIS_SCHOOLS.SCH_GROUP_HOUR ";

    public static final String TABLE_COM_SUBREGION = " MMIS_COMMON.COM_SUBREGION ";
    public static final String ACTION_MOVE_SCHOOL_COMMON_INFO="moveSchoolCommonInfo";
}
