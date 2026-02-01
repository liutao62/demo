<p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 2
<strong>输出：</strong>
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>[[1]]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 20</code></li> 
 <li><code>1 &lt;= k &lt;= n</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>回溯</details><br>

<div>👍 1818, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/issues' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：为满足广大读者的需求，网站上架 [速成目录](https://labuladong.online/algo/intro/quick-learning-plan/)，如有需要可以看下，谢谢大家的支持~**



<p><strong><a href="https://labuladong.online/algo/essential-technique/permutation-combination-subset-all-in-one/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>


<div id="labuladong_solution_zh">

## 基本思路

这也是典型的回溯算法，`k` 限制了树的高度，`n` 限制了树的宽度，继续套我们以前讲过的 [回溯算法模板框架](https://labuladong.online/algo/essential-technique/backtrack-framework/) 就行了：

![](https://labuladong.online/algo/images/subset/2.jpg)

**详细题解**：
  - [回溯算法秒杀所有排列/组合/子集问题](https://labuladong.online/algo/essential-technique/permutation-combination-subset-all-in-one/)

</div>





<div id="solution">

## 解法代码



<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">cpp🤖</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">java🟢</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item " data-tab-group="default"><div class="highlight">

```cpp
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

#include <vector>

class Solution {
private:
    std::vector<std::vector<int>> res;

    void backtrack(int n, int k, int start, std::vector<int>& track) {
        // 到达树的底部
        if (k == track.size()) {
            res.push_back(track);
            return;
        }
        // 注意 i 从 start 开始递增
        for (int i = start; i <= n; i++) {
            // 做选择
            track.push_back(i);
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.pop_back();
        }
    }

public:
    std::vector<std::vector<int>> combine(int n, int k) {
        if (k <= 0 || n <= 0) return res;
        std::vector<int> track;
        backtrack(n, k, 1, track);
        return res;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译。
# 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

class Solution:
    def __init__(self):
        self.res = []

    def combine(self, n: int, k: int) -> List[List[int]]:
        if k <= 0 or n <= 0:
            return self.res
        track = []
        self.backtrack(n, k, 1, track)
        return self.res

    def backtrack(self, n: int, k: int, start: int, track: List[int]):
        # 到达树的底部
        if k == len(track):
            self.res.append(track[:])
            return
        # 注意 i 从 start 开始递增
        for i in range(start, n + 1):
            # 做选择
            track.append(i)
            self.backtrack(n, k, i + 1, track)
            # 撤销选择
            track.pop()
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n <= 0) return res;
        List<Integer> track = new ArrayList<>();
        backtrack(n, k, 1, track);
        return res;
    }

    private void backtrack(int n, int k, int start, List<Integer> track) {
        // 到达树的底部
        if (k == track.size()) {
            res.add(new ArrayList<>(track));
            return;
        }
        // 注意 i 从 start 开始递增
        for (int i = start; i <= n; i++) {
            // 做选择
            track.add(i);
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

var res [][]int

func combine(n int, k int) [][]int {
    res = [][]int{}
    if k <= 0 || n <= 0 {
        return res
    }
    var track []int
    backtrack(n, k, 1, track)
    return res
}

func backtrack(n int, k int, start int, track []int) {
    // 到达树的底部
    if k == len(track) {
        temp := make([]int, len(track))
        copy(temp, track)
        res = append(res, temp)
        return
    }
    // 注意 i 从 start 开始递增
    for i := start; i <= n; i++ {
        // 做选择
        track = append(track, i)
        backtrack(n, k, i+1, track)
        // 撤销选择
        track = track[:len(track)-1]
    }
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

var combine = function(n, k) {
    let res = [];

    var backtrack = function(n, k, start, track) {
        // 到达树的底部
        if (k === track.length) {
            res.push([...track]);
            return;
        }
        // 注意 i 从 start 开始递增
        for (let i = start; i <= n; i++) {
            // 做选择
            track.push(i);
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.pop();
        }
    }

    if (k <= 0 || n <= 0) return res;
    let track = [];
    backtrack(n, k, 1, track);
    return res;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🌟🌟 算法可视化 🌟🌟</strong></summary><div id="data_combinations"  category="leetcode" ></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_combinations"></div></div>
</details><hr /><br />

</div>
</details>
</div>

