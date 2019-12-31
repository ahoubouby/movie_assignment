import React  from 'react';
import { Grid, Image, Card } from 'semantic-ui-react';
import { useDispatch, useSelector } from 'react-redux';

export const FullGrid = () => {
const { movies } = useSelector(state => ({ movies: state.movies || [] }));
const dispatch = useDispatch();
	return (
		<Grid>
			{(movies.length > 0 ) &&
				<Grid.Row columns={4}>
					{movies.map((item, index) => <GridCulom key={index} item={item.item} />)}
				</Grid.Row>
			}
		</Grid>
	);
};

/*
"title" : "Avengers: Infinite War",
  "director" : "Anthony Russo, Joe Russo",
  "releaseDate" : "25/04/2018",
  "type" : "ACTION"
*/
const GridCulom = ({ title, releaseDate, type }) => {
	return (
		<Grid.Column>
			<Card>
				<Image src="https://react.semantic-ui.com/images/wireframe/image.png" />
				<Card.Content>
					<Card.Header>{title}</Card.Header>
					<Card.Meta>
						<span className="date">{releaseDate}</span>
					</Card.Meta>
					<Card.Description>{type}</Card.Description>
				</Card.Content>
			</Card>
		</Grid.Column>
	);
};
