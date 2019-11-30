import java.io.*;
import java.util.*;
class MyClass {
    static long M = 1000000007;//9
    static long oneArr[];
    static long tenArr[];
    static int lim = 200000;
    static long mod(String a){
        long res = 0L;
        for(int i=0;i<a.length();i++){
            res = ((res*10L)%M+(a.charAt(i)-'0'))%M;
        }
        return res;
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        oneArr = new long[lim];tenArr = new long[lim];
        oneArr[0] = 0;tenArr[0] = 1;tenArr[1] = 1;
        for(int i=1;i<lim-2;i++){
            oneArr[i] = ((oneArr[i-1]*10)%M +1)%M;
            tenArr[i+1] = (tenArr[i]*10)%M;
        }
        while(t-->0){
            String[] ss = br.readLine().trim().split("\\s+");
            String[] dd = br.readLine().trim().split("\\s+");
            int al = Integer.parseInt(ss[0]), bl = Integer.parseInt(dd[0]);
            String a = ss[1].trim(), b = dd[1].trim();
            long arr[] = new long[a.length()];
            long times = 1L, prev = 0L;
            for(int i=a.length()-1;i>=0;i--){
                arr[i] = ((prev)%M + (times*(a.charAt(i)-'0'))%M)%M;
                prev = arr[i]%M;
                times = (times*10)%M;
            }
            //for(int i=0;i<a.length();i++) System.out.println(arr[i]);
            long brr[] = new long[b.length()];
            times = 1L;prev = 0L;
            for(int i=b.length()-1;i>=0;i--){
                brr[i] = ((prev)%M + (times*(b.charAt(i)-'0'))%M)%M;
                prev = brr[i]%M;
                times = (times*10)%M;
            }
            
            long aa = mod(a), bb = mod(b);
            long aaa = (aa%M - 1 + M)%M;
            //System.out.println(a+" "+aa+" G "+b+" "+bb);
            long Amatter = (((aa*aaa))/2L)%M, Bmatter = (((bb*(bb+1L)))/2L)%M;
            //System.out.println(aa*(aa-1)+" "+(bb*(bb+1))%MOD);
            //System.out.println(Amatter+" "+(aa*aaa)/2L+" G "+Bmatter);
            
            long val = 0L;
            for(int i=0;i<a.length()-1;i++){
                val = (val%M + punisher(a,i,arr,false)%M)%M;
                //val = (val%M + punisher(a.substring(i,a.length()),false)%M)%M;
            }
            //System.out.println("VAL "+val);
            long resA = (Amatter%M - val%M + M )%M;
            //System.out.println("RESA "+resA);
            
            long lav = 0L;
            for(int i=0;i<b.length()-1;i++){
                lav = (lav%M + punisher(b,i,brr,true)%M)%M;
                //lav = (lav%M + punisher(b.substring(i,b.length()),true)%M)%M;
            }
            //System.out.println("LAV "+lav);
            long resB = (Bmatter%M-lav%M +M )%M;
            //System.out.println("RESB "+resB);
            long solution = (resB%M-resA%M + M)%M;
            System.out.println(solution);
        }
    }
    
    static long punisher(String a, int i, long[] crr, boolean isR){
        //System.out.println("LOOK "+a);
        int first = a.charAt(i)-'0';
        int l = a.length()-1-i;
        //System.out.println("FIRST "+first+"  LEN "+l);
        
        //String ones = (l==1)? "0" :String.join("", Collections.nCopies(l-1,"1"));
        //String tens = "1"+ ((l==1)? "" : String.join("", Collections.nCopies(l-2,"0")));
        //System.out.println(ones+" "+tens);
        //long onesMOD = mod(ones), tensMOD = mod(tens);
        long onesMOD = oneArr[l-1];
        long tensMOD = tenArr[l-1];
        //System.out.println(onesMOD+" "+mod(ones)+" "+tensMOD+" "+mod(tens));
        //System.out.println(tens+" "+tensMOD);
        long addMOD = (((((45*onesMOD)%M)*tensMOD)%M)*first)%M;
        //System.out.println(onesMOD+" "+tensMOD+" ADD "+addMOD);
        
        //String fact = "1" + String.join("", Collections.nCopies(l-1,"0"));
        //long factMer = mod(fact);
        long factM = tenArr[l];
        //System.out.println(factMer+" FACT "+factM);
        long till = a.charAt(i)-'0';
        if(a.charAt(i)<a.charAt(i+1)) till=till+1L;
        long sum = (till*(till-1L))/2L;
        //System.out.println(factM+" F "+sum);
        long thenMOD = ((((factM%M)*(sum%M))%M)*(factM%M))%M;
        //System.out.println(thenMOD);
        if(a.charAt(i)==a.charAt(i+1)){
            long ss = (a.length()==(2+i))? 0L : crr[2+i];
            
            //String ssss = (a.length()==(2+i))? "0":a.substring(2+i,a.length());
            //System.out.println(ss+" "+mod(ssss));
            long sss = (isR)?(ss+1L)%M : (ss)%M;
            //if(isR) System.out.println(sss+" "+ss);
            //System.out.println(sss+" "+(mod(ssss)+1L));
            
            //System.out.println(sss+" KK "+(((sss*(a.charAt(1)-'0'))%M)*factM)+" "+factM);
            thenMOD = (thenMOD%M + (((sss*(a.charAt(i+1)-'0'))%M)*factM)%M)%M;
            //System.out.println((sss+" "+(a.charAt(i+1)-'0')+" "+factM));
        }
        //System.out.println("SENDING "+(thenMOD+addMOD)%M);
        return (thenMOD+addMOD)%M;
    }
}