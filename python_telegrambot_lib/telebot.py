class robotPush:
    # robotPush(token,groupID).postAudio(fileroad,info,name):
    def __init__(self, token, ID):
        import telebot
        self.BOT = telebot.TeleBot(token)  # You can set parse_mode by default. HTML or MARKDOWN
        self.objectID = ID

    def postVideo(self, file, source, name):
        if os.path.exists(file):
            video = open(file, 'rb')
            self.BOT.send_video(self.objectID, video, source, name, name)
            # '#音乐MV #AUTOrunning '+str(source)+"   "+name
            # 显示要求为MP4--https://mlog.club/article/5018822
            print("============Already upload this video============")
            video.close()
            return file

    def postAudio(self, file, source, name):
        if os.path.exists(file):
            audio = open(file, 'rb')
            self.BOT.send_audio(self.objectID, audio, source, name, name)
            # '#音乐提取 #AUTOrunning '+str(source)+"   "+name
            print("============ALready upload this flac============")
            audio.close()
            return file
