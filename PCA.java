import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import Jama.*;
public class PCA
{
    double data[][];
    double cov[][];
    double feat[][];
    Double result[][];
    int h;
    int w;
    int dimension;
    public PCA(int dimension) {
        this.dimension=dimension;
    }

    public void load(String dataFile)
    {
        ArrayList<String> temp = new ArrayList<>();
        String T;
        try {
            BufferedReader file=new BufferedReader(new FileReader(dataFile));
            while (true){
                T=file.readLine();
                if (T==null) break;
                temp.add(T);
            }

            h=temp.size();
            w=temp.get(0).split(" ").length;

            double data[][]=new double[h][w];
            for (int i = 0; i < h; i++) {
                int j = 0;
                for (String s : temp.get(i).split(" ")) {
                    data[i][j]=Double.valueOf(s);
                    j++;
                }
            }
            this.data=data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    public void store(String dataFile)
    {
        ArrayList<String> temp = new ArrayList<>();
        String T;
        try {
            BufferedWriter file=new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < h ; i++) {
                for (int j = 0; j < dimension ; j++) {
                    file.write(result[i][j].toString());
                    if (j <dimension-1)
                        file.write(" ");
                }
                file.write("\n");
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    public double[] mean(){
        double mean[]=new double[w];
        for (int i = 0; i < w; i++) {
            double sum=0;
            for (int j = 0; j < h; j++) {
                sum+=data[j][i];
            }
            mean[i]=sum/h;
        }
        return mean;
    }

    public  void cent(){
        double mean[]=mean();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                data[i][j]-=mean[j];
            }
        }
    }

    public void cov(){
        double result[][]=new double[w][w];
        result=mult(trans(data),data);
        cov=result;
    }

    public double[][] mult(double A[][],double B[][]){
        int h=A.length;
        int w=B[0].length;
        int b=A[0].length;
        double result[][]=new double[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < b; k++) {
                    result[i][j]+=A[i][k]*B[k][j];
                }
            }
        }
        return result;
    }

    public double[][] trans(double[][] M){
        int h=M.length;
        int w=M[0].length;
        double result[][]=new double[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                result[i][j]=M[j][i];
            }
        }
        return result;
    }

    public void eig(){
        Matrix matrix=new Matrix(cov);
        Matrix temp=matrix.eig().getV();
        double result[][]=new double[w][dimension];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < dimension ; j++) {
                result[i][j]=temp.get(i,j);
            }
        }
        this.feat = result;
    }

    public void result(){
        double temp[][]=new double[h][dimension];
        Double result[][]=new Double[h][dimension];
        temp=mult(data,feat);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < dimension; j++) {
                result[i][j]=temp[i][j];
            }
        }
        this.result=result;
    }

    public static void main(String[] args) {
        PCA a=new PCA(2);
        a.load("data.txt");
        a.cent();
        a.cov();
        a.eig();
        a.result();
        a.store("result.txt");
    }
}