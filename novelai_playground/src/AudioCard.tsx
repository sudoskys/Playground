import React from 'react';
import {Box, Card, CardContent, Typography, IconButton, useTheme} from '@mui/material';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import PauseIcon from '@mui/icons-material/Pause';

export interface AudioCardProps {
    title: string;
    subTitle: string;
    audioUrl: string;
}

export const AudioCard: React.FC<AudioCardProps> = ({title, subTitle, audioUrl}) => {
    const theme = useTheme();
    const [isPlaying, setIsPlaying] = React.useState(false);
    const audioRef = React.useRef<HTMLAudioElement>(new Audio(audioUrl));

    const handlePlayPauseClick = () => {
        if (isPlaying) {
            audioRef.current.pause();
        } else {
            audioRef.current.play();
        }
        setIsPlaying(!isPlaying);
    };

    React.useEffect(() => {
        audioRef.current.addEventListener('ended', () => setIsPlaying(false));
        return () => {
            audioRef.current.removeEventListener('ended', () => setIsPlaying(false));
        };
    }, []);

    return (
        <Card sx={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center', padding: 2, border: 1, borderColor: 'divider', marginBottom: 2}}>
            <Box sx={{flex: '1 1', pr: 2}}>
                <CardContent sx={{padding: 1}}>
                    <Typography
                        component="div"
                        variant="subtitle1"
                        noWrap
                        sx={{
                            overflow: 'hidden',
                            textOverflow: 'ellipsis',
                            whiteSpace: 'nowrap',
                            fontSize: theme.typography.pxToRem(16),
                        }}
                    >
                        {title}
                    </Typography>
                    <Typography
                        variant="caption"
                        color="text.secondary"
                        noWrap
                        sx={{
                            overflow: 'hidden',
                            textOverflow: 'ellipsis',
                            whiteSpace: 'nowrap',
                            fontSize: theme.typography.pxToRem(12),
                        }}
                    >
                        Seed: {subTitle}
                    </Typography>
                </CardContent>
            </Box>
            <IconButton aria-label="play/pause" onClick={handlePlayPauseClick} sx={{color: isPlaying ? 'success.main' : 'lightsteelblue'}}>
                {isPlaying ? <PauseIcon/> : <PlayArrowIcon/>}
            </IconButton>
        </Card>
    )
};