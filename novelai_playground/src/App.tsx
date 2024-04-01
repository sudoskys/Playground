import React, {useEffect, useState} from "react";
import ProTip from './ProTip';
import {Dialog, DialogActions, DialogContent, DialogTitle, LinearProgress} from '@mui/material';
import {uniqueNamesGenerator, adjectives, colors, animals} from 'unique-names-generator';
import {Box, Link, Typography, Container, IconButton} from "@mui/material";
import {Button, Card, CardContent, TextField} from "@mui/material";
import {useMediaQuery} from '@mui/material';
import {Grid} from '@mui/material';
import {Casino, LockOpen, Lock} from '@mui/icons-material';
import {AudioCard, AudioCardProps} from './AudioCard.tsx';
import {FixedSizeList} from "react-window";
import {useSpring, animated} from 'react-spring';
import AutoSizer from "react-virtualized-auto-sizer";

// 对于 AudioCard，我们将其包装到 React.memo 中，以免不必要的重绘
const MemoizedAudioCard = React.memo(AudioCard);

const lockOpen = <LockOpen/>;
const lock = <Lock style={{color: 'green'}}/>;

function Copyright() {
    return (
        <Typography variant="body2" color="text.secondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="https://github.com/sudoskys">
                sudoskys
            </Link>{' '}
            {new Date().getFullYear()}.
        </Typography>
    );
}

function MainPage({setResult}: { setResult: React.Dispatch<React.SetStateAction<AudioCardProps[]>> }) {
    const buildAudioData = (title: string, audioUrl: string, seed: string) => {
        // 截取title的前10个字符
        return {
            // imageUrl: `https://source.unsplash.com/featured/?${seed}`,
            title: `${title}`,
            subTitle: `${seed}`,
            audioUrl: audioUrl,
        };
    }
    const [apiKey, setApiKey] = useState(localStorage.getItem('NOVELAI_API_KEY') || '');
    const [showAPIKeyDialog, setShowAPIKeyDialog] = useState(false);
    const [isKeyLocked, setIsKeyLocked] = useState(apiKey !== '');
    // Loading
    const [isLoading, setIsLoading] = useState(false);
    // 输入框的预设
    const [prompt, setPrompt] = useState<string>("Hello World, This is a test for NovelAI");
    const [seed, setSeed] = useState<string>("Ananas");
    // 倒计时
    const [countdown, setCountdown] = useState(10); // 初始倒计时为10秒
    useEffect(() => {
        const timer = countdown > 0 && setInterval(() => setCountdown(countdown - 1), 1000);
        return () => {
            // eslint-disable-next-line @typescript-eslint/ban-ts-comment
            // @ts-expect-error
            clearInterval(timer);
        };
    }, [countdown]);
    const onApiKeySubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        localStorage.setItem('NOVELAI_API_KEY', apiKey);
        setIsKeyLocked(true);
        setShowAPIKeyDialog(false);
        // Add additional verification against your server if needed
    };
    const onApiKeyClick = () => {
        if (isKeyLocked) {
            setApiKey('');
            setIsKeyLocked(false);
            localStorage.removeItem('NOVELAI_API_KEY');
        } else {
            setShowAPIKeyDialog(true);
        }
    };
    const updateApiKey = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setApiKey(event.target.value);
    };
    const generateRandomSeed = () => {
        const randomName: string = uniqueNamesGenerator({
            dictionaries: [adjectives, colors, animals]
        }); // big_red_donkey
        setSeed(randomName);
    }
    const generateVoice = async () => {
        setIsLoading(true);
        const response = await fetch('/api/generate-voice?text=' + encodeURIComponent(prompt) + '&voice=-1&seed=' + encodeURIComponent(seed) + '&opus=false&version=v2', {
            method: 'GET',
            mode: 'cors',
            redirect: 'follow',
            credentials: 'include',
            headers: {
                'accept': 'application/json',
                'Authorization': `Bearer ${apiKey}`
                //import.meta.env.VITE_NOVELAI_API_KEY || '',
            },
        });
        const buffer = await response.arrayBuffer();
        // console.log(buffer);
        const blob = new Blob([buffer], {type: 'audio/mp3'});
        const url = URL.createObjectURL(blob);
        // console.log(url);
        // 添加到列表
        setResult(oldArray => [...oldArray, buildAudioData(prompt, url, seed)]);
        // 重置Seed
        generateRandomSeed();
        setIsLoading(false);
    }

    return (
        <Card variant="outlined">
            <CardContent sx={{p: 3}}>
                {/*标题*/}
                <Box sx={{display: "flex", alignItems: "center"}}>
                    <Typography variant="h5" component="h5" sx={{mb: 2}}>
                        Prompt
                    </Typography>
                    <IconButton onClick={onApiKeyClick} sx={{justifySelf: 'flex-end', marginLeft: 'auto'}}>
                        {isKeyLocked ? lock : lockOpen}
                    </IconButton>
                </Box>
                {/* The rest of your component */}
                <Dialog open={showAPIKeyDialog} onClose={onApiKeyClick}>
                    <DialogTitle id="form-dialog-title">Enter your API Key:</DialogTitle>
                    <DialogContent>
                        <TextField
                            autoFocus
                            id="apikey-dialog"
                            type="text"
                            fullWidth
                            value={apiKey}
                            onChange={updateApiKey}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={onApiKeySubmit} type="submit" disabled={apiKey === ''}>
                            Save
                        </Button>
                    </DialogActions>
                </Dialog>
                {/* 添加一个文本输入*/}
                <TextField
                    id="prompt"
                    label="prompt"
                    variant="outlined"
                    fullWidth={true}
                    multiline={true}
                    rows={4}
                    placeholder={"Please input your text here"}
                    defaultValue={prompt}
                    onChange={(event) => setPrompt(event.target.value)}
                >
                </TextField>
                {/* 添加一个 Seed*/}
                <Typography variant="h6" component="h6" sx={{mt: 2}}>
                    Seed
                </Typography>
                <Box
                    sx={{
                        width: 500,
                        maxWidth: '100%',
                        alignItems: 'center',
                        display: 'flex',
                    }}
                >
                    <TextField fullWidth
                               label="seed"
                               id="seed"
                               variant="outlined"
                               placeholder={"Please input your seed here"}
                               value={seed}
                               onChange={(event) => setSeed(event.target.value)}
                    />
                    <IconButton onClick={generateRandomSeed} sx={{ml: 1}}>
                        <Casino/>
                    </IconButton>
                </Box>
                <br/>
                {/* 添加一个生成按钮*/}
                <Button variant="contained" sx={{mt: 2}} onClick={generateVoice} disabled={isLoading}>
                    Generate
                </Button>
                <br/>
            </CardContent>
            <Box sx={{width: '100%'}}>
                {isLoading && <LinearProgress/>}
            </Box>
        </Card>
    )
}

function ResultSection({result, is_mob}: { result: AudioCardProps[], is_mob: boolean }) {
    const Row = ({index, style, data}: { index: number, style: NonNullable<unknown>, data: AudioCardProps[] }) => {
        // Note: We invert the order to display the newest items at the top
        const audioData = data[data.length - index - 1];
        return (
            <div style={{...style, padding: '5px'}} key={audioData.audioUrl}>
                <MemoizedAudioCard
                    title={audioData.title}
                    subTitle={audioData.subTitle}
                    audioUrl={audioData.audioUrl}
                />
            </div>
        );
    };

    const margin_top = is_mob ? 2 : 0; // Adjust the margin after title

    return (
        result.length > 0 && (
            // The Card component now fills its parent element
            <Card variant="outlined" sx={{mt: margin_top, height: '500px', width: '100%'}} style={{flexGrow: 1}}>
                <CardContent sx={{p: 3, height: '100%'}}>
                    <Typography variant="h5" component="h5" sx={{mb: 1}}>
                        Result
                    </Typography>
                    <AutoSizer>
                        {({height, width}) => (
                            <FixedSizeList
                                height={height}
                                width={width}
                                itemCount={result.length}
                                itemSize={115}
                                itemData={result}
                            >
                                {Row}
                            </FixedSizeList>
                        )}
                    </AutoSizer>
                </CardContent>
            </Card>
        )
    );
}

export default function App() {
    const isMobile = useMediaQuery('(max-width:600px)');
    const [result, setResult] = useState<AudioCardProps[]>([]);
    const animationProps = useSpring({
        from: result.length > 0 ? {opacity: 1, transform: 'translateY(0%)'} : {
            opacity: 0,
            transform: 'translateY(-50%)'
        },
        to: {opacity: 1, transform: 'translateY(0%)'},
        config: {
            tension: 210,
            friction: 20
        }
    });

    const direction = result.length > 0 ? 'row' : 'column';
    const align = result.length > 0 ? "flex-start" : "center";
    return (
        <Container maxWidth="lg">
            <Box sx={{my: 4}}>
                <Typography variant="h4" component="h1" sx={{mb: 2}}>
                    NovelAI
                </Typography>
                <Grid container spacing={3} direction={direction} alignItems={align} style={{display: 'flex'}}>
                    <Grid item md={6} xs={12}>
                        <animated.div style={animationProps}>
                            <MainPage setResult={setResult}/>
                        </animated.div>
                    </Grid>
                    {!isMobile && result.length > 0 && (
                        <Grid item md={6} xs={12}>
                            <ResultSection result={result} is_mob={isMobile}/>
                        </Grid>
                    )}
                </Grid>
                {isMobile && result.length > 0 && (
                    <ResultSection result={result} is_mob={isMobile}/>
                )}
                <ProTip/>
                <Copyright/>
            </Box>
        </Container>
    );
}