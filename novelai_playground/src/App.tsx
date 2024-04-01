import React, {useEffect, useState} from "react";
import ProTip from './ProTip';
import {LinearProgress} from '@mui/material';
import {uniqueNamesGenerator, adjectives, colors, animals} from 'unique-names-generator';
import {Box, Link, Typography, Container, IconButton} from "@mui/material";
import {Button, Card, CardContent, TextField} from "@mui/material";
import {useMediaQuery} from '@mui/material';
import {Grid} from '@mui/material';
import {Casino} from '@mui/icons-material';
import {AudioCard, AudioCardProps} from './AudioCard.tsx';
import {FixedSizeList} from "react-window";
import {useSpring, animated} from 'react-spring';

// 对于 AudioCard，我们将其包装到 React.memo 中，以免不必要的重绘
const MemoizedAudioCard = React.memo(AudioCard);


function Copyright() {
    return (
        <Typography variant="body2" color="text.secondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
                Your Website
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
    const generateRandomSeed = () => {
        const randomName: string = uniqueNamesGenerator({
            dictionaries: [adjectives, colors, animals]
        }); // big_red_donkey
        setSeed(randomName);
    }
    const generateVoice = async () => {
        setIsLoading(true);
        const response = await fetch('https://api.novelai.net/ai/generate-voice?text=' + encodeURIComponent(prompt) + '&voice=-1&seed=' + encodeURIComponent(seed) + '&opus=false&version=v2', {
            method: 'GET',
            mode: 'cors',
            redirect: 'follow',
            credentials: 'include',
            headers: {
                'accept': 'application/json',
                'Authorization': 'Bearer ' + import.meta.env.VITE_NOVELAI_API_KEY || '',
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
        setIsLoading(false);
    }

    return (
        <Card variant="outlined">
            <CardContent sx={{p: 3}}>
                {/*标题*/}
                <Typography variant="h5" component="h5" sx={{mb: 2}}>
                    Prompt
                </Typography>
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
                <Typography variant="h6" component="h6" sx={{mt: 2}}>
                    Countdown: {countdown}
                </Typography>
                {/*加10秒*/}
                <Button variant="contained" sx={{mt: 2}} onClick={() => setCountdown(countdown + 10)}>
                    Add 10S
                </Button>
            </CardContent>
            <Box sx={{width: '100%'}}>
                {isLoading && <LinearProgress/>}
            </Box>
        </Card>
    )
}

function ResultSection({result}: { result: AudioCardProps[] }) {
    const renderRow = ({index, style}: any) => {
        const audioData = result[result.length - index - 1];
        return (
            <div style={style} key={audioData.audioUrl}>
                <MemoizedAudioCard
                    title={audioData.title}
                    subTitle={audioData.subTitle}
                    audioUrl={audioData.audioUrl}
                />
            </div>
        );
    };

    return (
        result.length > 0 && (
            <Card variant="outlined" sx={{mt: 2}}>
                <CardContent sx={{p: 3}}>
                    <Typography variant="h5" component="h5" sx={{mb: 2}}>
                        Result
                    </Typography>
                    <br/>
                    <FixedSizeList
                        height={300}
                        width="100%"
                        itemCount={result.length}
                        itemSize={100}
                    >
                        {renderRow}
                    </FixedSizeList>
                </CardContent>
            </Card>
        )
    );
}


// 将 result 状态提升到 App 组件，并通过 props 传给 MainPage 和 ResultSection
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
    // 如果没有结果，将主要内容放在中间，有结果时，将主要内容放在左侧
    const align = result.length > 0 ? 'flex-start' : 'center';
    return (
        <Container maxWidth="lg">
            <Box sx={{my: 4}}>
                <Typography variant="h4" component="h1" sx={{mb: 2}}>
                    NovelAI
                </Typography>
                <Grid container spacing={3} direction={direction} alignItems={align}>
                    <Grid item md={6} xs={12}>
                        <animated.div style={animationProps}>
                            <MainPage setResult={setResult}/>
                        </animated.div>
                    </Grid>
                    {!isMobile && result.length > 0 && (
                        <Grid item md={6} xs={12}>
                            <ResultSection result={result}/>
                        </Grid>
                    )}
                </Grid>
                {isMobile && result.length > 0 && (
                    <>
                        <ResultSection result={result}/>
                    </>
                )}
                <ProTip/>
                <Copyright/>
            </Box>
        </Container>
    );
}