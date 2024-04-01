import LikeButton from './like-button';
import {ListItemIcon, ListItemText, MenuItem, MenuList, Paper, Typography} from "@mui/material";
import ContentCut from '@mui/icons-material/ContentCut';

function Header({title}) {
    return <h1>Develop. Preview. Ship {title}.</h1>;
}

export default function HomePage() {

    const names = ['Ada Lovelace', 'Grace Hopper', 'Margaret Hamilton'];
    return (<div>
        {/* Nesting the Header component */}
        <h1>H1</h1>
        <Header title="React"/>
        <Paper sx={{width: 320, maxWidth: '100%'}}>
            <MenuList>
                <MenuItem>
                    <ListItemText>Cut</ListItemText>
                </MenuItem>
                {names.map((name) => (<MenuItem><ListItemText>{name}</ListItemText></MenuItem>))}
            </MenuList>
        </Paper>
        <LikeButton/>
    </div>);
}
