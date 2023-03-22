# -*- coding: utf-8 -*-
# @Time    : 10/6/22 2:08 PM
# @FileName: 13_romanToInt.py
# @Software: PyCharm
# @Github    ：sudoskys
class Solution(object):
    def __init__(self):
        self.RomanMap = {
            "I": 1,
            "V": 5,
            "X": 10,
            "L": 50,
            "C": 100,
            "D": 500,
            "M": 1000,
        }

    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        _sum = 0
        # control
        for key in range(0, len(s) - 1):
            _now=self.RomanMap[s[key]]
            _next=self.RomanMap[s[key + 1]]
            _control = + _now
            if _now < _next:
                _control = -_now
            _sum += _control
        _sum += self.RomanMap[s[len(s)-1]]
        return _sum


sr = "IIID"
res = Solution().romanToInt(sr)
print(res)
