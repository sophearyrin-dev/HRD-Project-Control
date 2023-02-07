import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import logger from "redux-logger";
import { combineReducers } from "redux";
import { projectTypeReducer } from "./redux/reducers/Schedule/scheduleType/scheduleTypeReducer";
import { documentScheduleReducer } from "./redux/reducers/Schedule/documnetSchedule/documentScheduleReducer";
import { courseReducer } from "./redux/reducers/Course/courseReducer";
import { generationReducer } from "./redux/reducers/Generation/generationReducer";
import { universityReducer } from "./redux/reducers/University/universityReducer";
import loginReducer from "./redux/reducers/Login/loginReducer";
import { dailyReportReducer } from "./redux/reducers/DailyReport/dailyreportReducer";
import { projectScheduleReducer } from "./redux/reducers/Schedule/projectSchedule/projectScheduleReducer";
import { dashboardReducer } from "./redux/reducers/Dashboard/dashboardReducer";
import { projectReducer } from "./redux/reducers/ProjectDivision/Project/projectReducer";
import { projectDivisionReducer } from "./redux/reducers/ProjectDivision/projectDivisionReducer";
import { userReducer } from "./redux/reducers/UserControl/usercontrolReducer";
import { authenticatedReducer } from "./redux/reducers/Authenticated/authenticatedReducer";
import { taskReducer } from "./redux/reducers/Task/taskReducer";

const initialState = {
  sidebarShow: "responsive",
};
// T
const changeStateReducer = (state = initialState, { type, ...rest }) => {
  switch (type) {
    case "set":
      return {
        ...state,
        ...rest,
      };
    default:
      return state;
  }
};

const rootReducers = combineReducers({
  projectTypeReducer: projectTypeReducer,
  courseReducer: courseReducer,
  generationReducer: generationReducer,
  universityReducer: universityReducer,
  changeStateReducer: changeStateReducer,
  documentScheduleReducer: documentScheduleReducer,
  loginReducer: loginReducer,
  dailyReportReducer: dailyReportReducer,
  projectScheduleReducer: projectScheduleReducer,
  dashboardReducer: dashboardReducer,
  projectReducer: projectReducer,
  projectDivisionReducer: projectDivisionReducer,
  userReducer: userReducer,
  authenticatedReducer: authenticatedReducer,
  taskReducer: taskReducer,
});

const allEnhancers = compose(applyMiddleware(logger, thunk));

const store = createStore(rootReducers, allEnhancers);
export default store;
