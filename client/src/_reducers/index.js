import { combineReducers } from 'redux';

import { authentication } from './authentication.reducer';
import { users } from './users.reducer';
import { alert } from './alert.reducer';
import { movies } from './movies.reducer';

const rootReducer = combineReducers({
	authentication,
	users,
	alert,
	movies
});

export default rootReducer;
