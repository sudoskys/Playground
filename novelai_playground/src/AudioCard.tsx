import React, {useState, useEffect, useRef} from "react";
import {Box, Card, CardContent, Typography, IconButton, useTheme, Snackbar} from "@mui/material";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import PauseIcon from "@mui/icons-material/Pause";
import SaveIcon from "@mui/icons-material/Save";
import {CopyToClipboard} from "react-copy-to-clipboard";

export interface AudioCardProps {
    title: string;
    subTitle: string;
    audioUrl: string;
}

export const AudioCard: React.FC<AudioCardProps> = ({title, subTitle, audioUrl}) => {
    const theme = useTheme();
    const [isPlaying, setIsPlaying] = useState(false);
    const audioRef = useRef<HTMLAudioElement>(new Audio(audioUrl));
    const [openSnackbar, setOpenSnackbar] = useState(false);

    const handlePlayPauseClick = () => {
        if (isPlaying) {
            audioRef.current.pause();
        } else {
            audioRef.current.play();
        }
        setIsPlaying(!isPlaying);
    };

    useEffect(() => {
        audioRef.current.addEventListener('ended', () => setIsPlaying(false));
        return () => {
            audioRef.current.removeEventListener('ended', () => setIsPlaying(false));
        };
    }, []);

    const handleCopy = () => {
        setOpenSnackbar(true);
    }

    const handleClose = () => {
        setOpenSnackbar(false);
    }

    return (
        <Card
            variant={"outlined"}
            sx={{
                display: 'flex',
                flexDirection: 'row',
                justifyContent: 'space-between',
                alignItems: 'center',
                padding: 1,
                border: 1,
                borderColor: 'divider',
                marginBottom: 2
            }}>
            <Box sx={{flex: '1 1', pr: 2, width: "80%"}}>
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
                    <CopyToClipboard text={subTitle} onCopy={handleCopy}>
                        <Typography
                            variant="caption"
                            color="text.secondary"
                            noWrap
                            sx={{
                                overflow: 'hidden',
                                textOverflow: 'ellipsis',
                                whiteSpace: 'nowrap',
                                fontSize: theme.typography.pxToRem(12),
                                cursor: 'pointer',
                            }}
                        >
                            Seed: {subTitle}
                        </Typography>
                    </CopyToClipboard>
                </CardContent>
            </Box>
            <Box sx={{display: 'flex', alignItems: 'center', width: "20%"}}>
                <IconButton aria-label="play/pause" onClick={handlePlayPauseClick}
                            sx={{color: isPlaying ? 'success.main' : 'royalblue', marginRight: 1}}>
                    {isPlaying ? <PauseIcon/> : <PlayArrowIcon/>}
                </IconButton>
                <a href={audioUrl} download>
                    <IconButton aria-label="save">
                        <SaveIcon/>
                    </IconButton>
                </a>
            </Box>
            <Snackbar
                anchorOrigin={{vertical: 'bottom', horizontal: 'right'}}
                open={openSnackbar}
                onClose={handleClose}
                autoHideDuration={2000}
                message="Seed copied to clipboard"
            />
        </Card>
    );
}