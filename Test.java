给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTrack(n,k,1,new ArrayList<>());
        return res;
    }
    private void backTrack(int n,int k,int index,List<Integer> row){
        if(k==0){
            res.add(new ArrayList(row));
            return;
        }
        for(int i=index;i<=n;i++){
            row.add(i);
            backTrack(n,k-1,i+1,row);
            row.remove(row.size()-1);
        }
    }
}

给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backTrack(nums,0,new ArrayList<>(),0);
        return res;
    }
    private void backTrack(int[] nums,int index,List<Integer> row,int len){
        if(len>nums.length){
            return;
        }
        res.add(new ArrayList(row));
        if(len==nums.length)
        return;
        for(int i=index;i<nums.length;i++){
            row.add(nums[i]);
            backTrack(nums,i+1,row,len+1);
            row.remove(row.size()-1);
        }
    }
}

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    private int row;
    private int col;
    public boolean exist(char[][] board, String word) {
        row=board.length;
        if(row==0) return false;
        col=board[0].length;
        char[] c=word.toCharArray();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]==c[0]){
                    if(dfs(board,i,j,c,0,new boolean[row*col])) 
                        return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board,int i,int j,char[] c,int k,boolean[] isVisited){
        if(k==c.length){
            return true;
        }
        if(i<0||j<0||i>=row||j>=col||board[i][j]!=c[k]||isVisited[i*col+j]){
            return false;
        }
        isVisited[i*col+j]=true;
        if(dfs(board,i+1,j,c,k+1,isVisited)||dfs(board,i-1,j,c,k+1,isVisited)||
          dfs(board,i,j+1,c,k+1,isVisited)||dfs(board,i,j-1,c,k+1,isVisited)){
            return true;
        }
        isVisited[i*col+j]=false;
        return false;
    }
}

给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。
class Solution {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backTrack(nums,0,new ArrayList<>(),new boolean[nums.length],0);
        return res;
    }
    private void backTrack(int[] nums,int index,List<Integer> row,boolean[] isVisited,int len){
        res.add(new ArrayList(row));
        if(len==nums.length){
            return;
        }
        for(int i=index;i<nums.length;i++){
            if(isVisited[i]){
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]&&!isVisited[i-1]){
                continue;
            }
            row.add(nums[i]);
            isVisited[i]=true;
            backTrack(nums,i+1,row,isVisited,len+1);
            isVisited[i]=false;
            row.remove(row.size()-1);
        }
    }
}

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/gray-code
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res=new ArrayList<>();
        res.add(0);
        int head=1;
        for(int i=0;i<n;i++){
            int size=res.size();
            
            for(int j=size-1;j>=0;j--){
                res.add(head+res.get(j));
            }
            head<<=1;
        }
        return res;
    }
}

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res=new ArrayList<>();
        int head=1;
        for(int i=0;i<(1<<n);i++){
            res.add(i^(i>>1));
        }
        return res;
    }
}