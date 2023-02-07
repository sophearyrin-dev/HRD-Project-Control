import {
  GET_PROJECTSCHEDULE,
  GET_PROJECTSCHEDULEONE,
  DELETE_PROJECTSCHEDULE,
  DELETE_PROJECTSCHEDULEONE,
  GET_PROJECT_SCHEDULE_USER,
} from "../../../actions/Schedule/projectSchedule/projectScheduleType";

const initialState = {
  projectSchedule: [],
  projectScheduleOne: [],
  projectScheduleUser: [],
};

export const projectScheduleReducer = (state = initialState, action) => {
  switch (action.type) {

    case GET_PROJECTSCHEDULE:
      return {
        ...state,
        projectSchedule: action.projectSchedule,
      };

    case GET_PROJECTSCHEDULEONE:
      return {
        ...state,
        projectScheduleOne: action.projectScheduleOne,
      };

    case DELETE_PROJECTSCHEDULE:
      return {
        ...state,
        projectType: state.projectSchedule.filter(
          (c) => c.typeId !== action.payLoad
        ),
      };

    case DELETE_PROJECTSCHEDULEONE:
      return {
        ...state,
        projectType: state.projectScheduleOne.filter(
          (c) => c.typeId !== action.payLoad
        ),
      };

    case GET_PROJECT_SCHEDULE_USER:
      let newValue = [action.projectScheduleUser.id,
        action.projectScheduleUser.status,
        action.projectScheduleUser.type.name,
        action.projectScheduleUser.type.generation.name,
        action.projectScheduleUser.type.course.name,
        action.projectScheduleUser.groupMeeting
      ];
      return {
        ...state,
        projectScheduleUser: newValue
      }
      default:
        return state;
  }
};