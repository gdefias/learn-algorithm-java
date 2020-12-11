package Questions.Others;
/*
* 给出n或边长（n=k*k）输出回型矩阵
* 输出：
* 1  2  3  4
* 12 13 14 5
* 11 16 15 6
* 10 9  8  7
*
* */


public class jinshanQuestion {
    public static void main(String[] args) {
        int n = 16;
        int k = getk(n);
        if (k == -1) {
            System.out.println("n error！");
        }

        //getArray1(k, k);

        printJZ(k);

    }

    //计算边长
    public static int getk(int n) {
        int k=0;
        for(int i=1; i<=n; i++) {
            if(i*i == n) {
                k = i;
            } else if(i*i > n) {
                break;
            }
        }
        if(k==0) {
            return -1;
        }
        return k;
    }

    //逆时针输出回形矩阵图
    public static void getArray1(int xInt, int yInt) {
        int xMax = xInt;
        int yMax = yInt;
        int arr[][]=new int[yMax][xMax];
        int x = 0, y = 0;
        int xMin = 0, yMin = 0;
        int size = xMax * yMax;
        boolean flag = true;
        for(int i=0;i<size;i++){
            arr[y][x] = i+1;
            if((y+1)<yMax && flag){
                y++;
            }else if((x+1)<xMax && flag){
                x++;
            }else {
                if(y>yMin){
                    y--;
                }else if(x>(xMin+1)){
                    x--;
                }else{
                    xMax--;
                    yMax--;
                    xMin++;
                    yMin++;
                    y++;
                    flag = true;
                }
            }
            if((y+1) == yMax && (x+1) == xMax){
                flag = false;
            }
        }

        //输出
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+",");
            }
            System.out.println();
        }
    }

    public static void printJZ(int n){
        int [][]A=new int[n][n];
        int t=1;
        int i=0;
        int j=0;
        for(int k=0;k<n/2;k++){//k代表第几圈
            for(i=k,j=k;j<n-k-1;j++){//第一条边
                A[j][i]=t++;
            }
            for(i=k,j=n-k-1;i<n-k-1;i++){//第二条边
                A[j][i]=t++;
            }
            for(i=n-k-1,j=n-k-1;j>k;j--){//第三条边
                A[j][i]=t++;
            }
            for(i=n-k-1,j=k;i>k;i--){//第四条边
                A[j][i]=t++;
            }
        }
        if(n%2==1){
            A[n/2][n/2]=t;
        }

        //for(i=0;i<n;i++){
        //    for (j=0;j<n;j++) {
        //        System.out.print(A[i][j]+" ");
        //    }
        //    System.out.println();
        //}

        for(i=0;i<A.length;i++){
            for (j=0;j<A[i].length;j++) {
                System.out.print(A[j][i]+" ");
            }
            System.out.println();
        }

    }

}
