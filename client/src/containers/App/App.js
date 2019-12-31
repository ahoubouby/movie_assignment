import React from 'react';
import { FullGrid } from '../FullGrid/FullGrid';
import { Container, Header, Message } from 'semantic-ui-react';
import { useDispatch, useSelector } from 'react-redux';

function App() {
	const { alert } = useSelector(state => ({ alert: state.alert }));
	const dispatch = useDispatch();
	return (
		<Container style={{ margin: 20 }}>
			<Header></Header>
			{alert && <Message success={true} header="" content="" />}
			<FullGrid />
		</Container>
	);
}

export default App;
