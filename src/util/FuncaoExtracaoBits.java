package src.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FuncaoExtracaoBits {
    public static short extractBits (short value, int bstart, int blength)
    {
        short mask = (short)((1 << blength) - 1);
        return (short)((value >> bstart) & mask);
    }

    public void memoryWrite (short addr, short value) {
        // Não usado diretamente, mas mantido como placeholder conforme padrão do professor
    }

    void loadBinary (String binaryName)
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(binaryName);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            long tamanhoArquivo = fileInputStream.getChannel().size();

            int numShorts = (int) (tamanhoArquivo / 2);

            for (int i = 0; i < numShorts; i++) {
                int low = dataInputStream.readByte() & 0x000000FF;
                int high = dataInputStream.readByte() & 0x000000FF;
                int value = (low | (high << 8)) & 0x0000FFFF;

                this.memoryWrite((short)i, (short)value);
            }

            dataInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}