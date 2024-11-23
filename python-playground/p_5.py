def 检查广告语(ad_text):
    prohibited_words = [
        "国家级", "世界级", "最高级", "唯一", "首个", "顶级", "独家",
        "最新", "最先进", "最好", "最强", "第一品牌", "世界领先", 
        "极致", "王牌", "领袖品牌", "独一无二", "绝无仅有", "史无前例"
    ]

    found_words = [word for word in prohibited_words if word in ad_text]
    
    # 反转列表
    found_words.reverse()
    if found_words:
        formatted_words = "  ".join([f"['{word}']" for word in found_words])
        return f"广告语不合法，使用了违禁词：{formatted_words}".strip()
    else:
        return "该广告语合法！"

ad_text_input = input("请输入您要检测的广告词：")
result = 检查广告语(ad_text_input)
print(result)
