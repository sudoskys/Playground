import LikeButton from './like-button';

function Header({title}) {
    return <h1>Develop. Preview. Ship {title}.</h1>;
}

export default function HomePage() {

    const names = ['Ada Lovelace', 'Grace Hopper', 'Margaret Hamilton'];
    return (<div>
        {/* Nesting the Header component */}
        <h1>H1</h1>
        <Header title="React"/>
        <ul>
            {names.map((name) => (<li key={name}>{name}</li>))}
        </ul>
        <LikeButton/>
    </div>);
}
