import {
  GET_GENERATION,
  DELETE_GENERATION,
} from "../../actions/Generation/generationActionTypes";
const initialState = {
  generation: [],
};

export const generationReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_GENERATION:
      return {
        ...state,
        generation: action.generation,
      };
    case DELETE_GENERATION:
      return {
        ...state,
        generation: state.generation.filter(
          (c) => c.generationId !== action.payLoad
        ),
      };

    default:
      return state;
  }
};