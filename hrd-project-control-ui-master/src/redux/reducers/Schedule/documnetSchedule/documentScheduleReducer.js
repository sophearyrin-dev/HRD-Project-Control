import {
  GET_DOCUMENTSCHEDULE,
  DELETE_DOCUMENTSCHEDULE,
  GET_DOCUMENTSCHEDULEONE,
  DELETE_DOCUMENTSCHEDULEONE,
  GET_DOCUMENT_SCHEDULE_USER,
} from "../../../actions/Schedule/documentSchedule/documentScheduleType";

const initialState = {
  documentSchedule: [],
  documentScheduleOne: [],
  documentScheduleUser: [],
};

export const documentScheduleReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_DOCUMENTSCHEDULE:
      return {
        ...state,
        documentSchedule: action.documentSchedule,
      };
    case GET_DOCUMENTSCHEDULEONE:
      return {
        ...state,
        documentScheduleOne: action.documentScheduleOne,
      };
    case DELETE_DOCUMENTSCHEDULE:
      return {
        ...state,
        projectType: state.documentSchedule.filter(
          (c) => c.typeId !== action.payLoad
        ),
      };
    case DELETE_DOCUMENTSCHEDULEONE:
      return {
        ...state,
        projectType: state.documentScheduleOne.filter(
          (c) => c.typeId !== action.payLoad
        ),
      };
    case GET_DOCUMENT_SCHEDULE_USER:
      let newValue = [
        action.documentScheduleUser.id,
        action.documentScheduleUser.status,
        action.documentScheduleUser.scheduleType.name,
        action.documentScheduleUser.scheduleType.generation.name,
        action.documentScheduleUser.scheduleType.course.name,
      ];
      return {
        ...state,
        documentScheduleUser: newValue,
      };

    default:
      return state;
  }
};
