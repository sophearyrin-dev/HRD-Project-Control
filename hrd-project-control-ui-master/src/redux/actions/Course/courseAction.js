import axios from "axios";
import {
  baseURI
} from "../../../baseURL/baseURL";
import authHeader from '../Login/securityUtils/authHeader';
export const GET_COURSE = "GET_COURSE";

export const getCourse = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/courses`, {
      headers: authHeader()
    });
    dp({
      type: GET_COURSE,
      course: result.data.data,
    });
  };
};