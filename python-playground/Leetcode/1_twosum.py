# -*- coding: utf-8 -*-
# @Time    : 10/6/22 12:35 PM
# @FileName: 1_twosum.py
# @Software: PyCharm
# @Github    ：sudoskys
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        record = dict()
        # 枚举数组
        for index, content in enumerate(nums):
            if target - content in record:
                # 检查到符合的数据，进行一个取索引和返回当前索引
                return [record[target - content], index]
            else:
                # 没有数据，存储下标
                record[content] = index


nums = [2, 7, 11, 15]
target = 9
res = Solution().twoSum(nums=nums, target=target)
print(res)
