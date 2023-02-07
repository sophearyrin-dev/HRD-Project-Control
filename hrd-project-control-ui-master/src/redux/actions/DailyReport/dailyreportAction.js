import axios from "axios";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import {
  GET_DAILYREPORT,
  GET_VIEWDAILYREPORT,
  GET_LISTREPORT,
  GET_DAILYREPORT_ASSIGN,
  GET_DAILYREPORT_DIVISION,
} from "../../actions/DailyReport/dailyreportActionTypes";

export const getDailyReport = (cId, gId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/projects/filter?course_id=${cId}&generation_id=${gId}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_DAILYREPORT,
      dailyreport: result.data.data,
    });
  };
};

export const getProjectFilter = (cId, gId) => {
  return async (dp) => {
    let check = false;

    const result = await axios.get(
      `${baseURI}/projects/filter?course_id=${cId}&generation_id=${gId}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_DAILYREPORT_ASSIGN,
      ProjectFilter: result.data.data,
      check: check,
    });
  };
};

export const getProjectDivi = (cId, gId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/projects/filter?course_id=${cId}&generation_id=${gId}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_DAILYREPORT_DIVISION,
      ProjectDivi: result.data.data,
    });
  };
};

export const getListReport = (cDate, pId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/daily-reports?current_date=${cDate}&project_id= ${pId}`,
      { headers: authHeader() }
    );
    // console.log(result.data.data);
    dp({
      type: GET_LISTREPORT,
      listreport: result.data.data,
    });
  };
};

export const getviewDailyReport = (rId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/daily-reports/filter?id=${rId}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_VIEWDAILYREPORT,
      viewdailyreport: result.data.data,
    });
  };
};
