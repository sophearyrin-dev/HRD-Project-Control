import {
  GET_DAILYREPORT,
  GET_VIEWDAILYREPORT,
  GET_LISTREPORT,
  GET_DAILYREPORT_ASSIGN,
  GET_DAILYREPORT_DIVISION,
} from "../../actions/DailyReport/dailyreportActionTypes";

const initialState = {
  dailyreport: [],
  viewdailyreport: [],
  listreport: [],

  ProjectFilter: [],
  ProjectDivi: [],
};

export const dailyReportReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_DAILYREPORT:
      return {
        ...state,
        dailyreport: action.dailyreport,
      };

    case GET_DAILYREPORT_ASSIGN:
      return {
        ...state,
        ProjectFilter: action.ProjectFilter,
      };

    case GET_DAILYREPORT_DIVISION:
      return {
        ...state,
        ProjectDivi: action.ProjectDivi,
      };
    case GET_VIEWDAILYREPORT:
      return {
        ...state,
        viewdailyreport: action.viewdailyreport,
      };
    case GET_LISTREPORT:
      return {
        ...state,
        listreport: action.listreport,
      };
    default:
      return state;
  }
};
