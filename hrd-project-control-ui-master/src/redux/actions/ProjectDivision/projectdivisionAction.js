import axios from "axios";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import {
  GET_PROJECTDIVISION,
  GET_PROJECTDIVISION_FEATURE,
  GET_SCHEDULE_FILTER,
} from "./projectdivisionActionTypes";
import { GET_UNAUTHENTICATED } from "../Authenticated/authenticatedType";

export const getProjectDivision = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/project-division?id=${id}`, {
      headers: authHeader(),
    });
    dp({
      type: GET_PROJECTDIVISION,
      projectDivision: result.data.data,
    });
  };
};

export const getScheduleByCourseNGen = (cId, genId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/schedule-type/filter?course_id=${cId}&generation_id=${genId}`,
      {
        headers: authHeader(),
      }
    );
    dp({
      type: GET_SCHEDULE_FILTER,
      shceduleFilter: result.data.data,
    });
  };
};

// http://104.197.139.95:1502/api/v1/schedule-type/filter?course_id=8&generation_id=2

export const getProjectDivisionFeatObj = (id) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/project-division/project?id=${id}`,
      {
        headers: authHeader(),
      }
    );
    dp({
      type: GET_PROJECTDIVISION_FEATURE,
      projectDivisionFeature: result.data.data,
    });
  };
};

export const assignProject = (member, cb) => {
  axios
    .post(`${baseURI}/project-division`, member, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    })
    .catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};
