package Final.Serveur.Interface;

import java.io.IOException;

public interface PhysiqueOut {
    void envoyer(byte[] aEnvoyer, int length) throws IOException;
}