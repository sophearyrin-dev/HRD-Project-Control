import {
  GET_USER_PROJECT,
  GET_USER_ISSUE,
  GET_ALL_PROJECT,
  GET_ALL_ISSUE,
} from "./dashboardActionTypes";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import Axios from "axios";

export const getUserProject = (id) => {
  let check = false;
  const getUserProject = async (db) => {
    const result = await Axios.get(`${baseURI}/project-division/users/${id}`, {
      headers: authHeader(),
    });
    result.data.data.map((data) =>
      data.courseId == 2 ? (check = true) : console.log("")
    );
    db({
      type: GET_USER_PROJECT,
      payload: result.data.data,
      check: check,
    });
  };
  return getUserProject;
};

export const getUserIssue = (id) => {
  let issue = 0;
  let progress = 0;
  const getUserIssue = async (db) => {
    const result = await Axios.get(`${baseURI}/tasks/${id}`, {
      headers: authHeader(),
    });
    if (result.data.data.length > 0) {
      issue = result.data.data[0].numberOfIssue;
      progress = result.data.data[0].projectProgress;
    }
    db({
      type: GET_USER_ISSUE,
      payload: [issue, progress],
    });
  };
  return getUserIssue;
};

export const getAllProject = (courseId, generationId) => {
  const getAllProject = async (db) => {
    const result = await Axios.get(
      `${baseURI}/tasks/all-project-progress?courseId=${courseId}&generationId=${generationId}`,
      { headers: authHeader() }
    );
    db({
      type: GET_ALL_PROJECT,
      payload: result.data.data,
    });
  };
  return getAllProject;
};

export const getAllIssues = (proId) => {
  const getAllIssues = async (db) => {
    const result = await Axios.get(`${baseURI}/subtasks-find-issue/${proId}`, {
      headers: authHeader(),
    });
    db({
      type: GET_ALL_ISSUE,
      payload: result.data.data,
    });
  };
  return getAllIssues;
};
