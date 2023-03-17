package br.com.valim.contratoapi.docusign;

import br.com.valim.contratoapi.config.EnvironmentConfig;
import br.com.valim.contratoapi.docusign.dtos.SignerDto;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth.OAuthToken;
import com.docusign.esign.client.auth.OAuth.UserInfo;
import com.docusign.esign.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Log4j2
@RequiredArgsConstructor
public class DSEnvelopeV1 {

    //@Value("${docusign.basepath}")
    //private EnvironmentConfig docusignBasePath;

    private final EnvironmentConfig docusignEnvironment;


    public String createEnvelope(ArrayList<SignerDto> listSigner, String docId, String docName, String docBase64) throws IOException, ApiException {
        //public String createEnvelope(String signerEmail, String signerName, String docId, String docName, String docBase64) throws IOException, ApiException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Setup OAuth token
        // Get access token and accountId
        ApiClient apiClient = new ApiClient(docusignEnvironment.getDocusignBasepath());
        apiClient.setOAuthBasePath(docusignEnvironment.getDocusignSetOAuthBasePath());
        ArrayList<String> scopes = new ArrayList<String>();
        scopes.add("signature");
        scopes.add("impersonation");
        //byte[] privateKeyBytes = Files.readAllBytes(Paths.get("C:\\Projetos-tagui\\contrato-api\\contrato-api\\docusign-privateKey.txt"));
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(docusignEnvironment.getDocusignPathPrivateKey()));
        OAuthToken oAuthToken = apiClient.requestJWTUserToken(
                docusignEnvironment.getDocusignChaveIntegracao(),
                docusignEnvironment.getDocusignUserId(),
                scopes,
                privateKeyBytes,
                3600);
        String accessToken = oAuthToken.getAccessToken();
        UserInfo userInfo = apiClient.getUserInfo(accessToken);
        String accountId = userInfo.getAccounts().get(0).getAccountId();
        apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);

        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);

        // Create envelopeDefinition object
        EnvelopeDefinition envelope = new EnvelopeDefinition();
        envelope.setEmailSubject("Por favor, assine o "+docName);

        // Create tabs object
        SignHere signHere = new SignHere();
        signHere.setDocumentId(docId);
        signHere.setPageNumber("1");
        signHere.setXPosition("191");
        signHere.setYPosition("148");

        signHere.tooltip("Favor, assinar aqui");
        //signHere.anchorString("below");
        signHere.setRecipientId("1");


        /*SignHere signHere2 = new SignHere();
        signHere2.setDocumentId("1");
        signHere2.setPageNumber("1");
        signHere2.setXPosition("194");
        signHere2.setYPosition("150");

        signHere2.tooltip("Assine aqui 2");
        signHere2.anchorString("below");
        signHere2.setRecipientId("3");*/

        Tabs tabs = new Tabs();
        tabs.addSignHereTabsItem(signHere);
        //tabs.addSignHereTabsItem(signHere2);
        //tabs.setSignHereTabs(Arrays.asList(signHere));
        tabs.setSignHereTabs(tabs.getSignHereTabs());


        /*Approve approve = new Approve();
        approve.setDocumentId("1");
        approve.setPageNumber("1");
        approve.setTooltip("AssineAqui");
        approve.setRecipientId("1");
        approve.setXPosition("10");
        approve.setYPosition("10");
        tabs.addApproveTabsItem(approve);*/

        /*Approve approve2 = new Approve();
        approve2.setDocumentId("1");
        approve2.setPageNumber("1");
        approve2.setTooltip("AssineAqui2");
        approve2.setRecipientId("3");
        approve2.setXPosition("15");
        approve2.setYPosition("15");
        tabs.addApproveTabsItem(approve2);*/

        //tabs.setApproveTabs(tabs.getApproveTabs());

        ArrayList<Signer> signers = new ArrayList<Signer>();

        // Set recipients - Signatarios
        for (int counter = 0; counter < listSigner.size(); counter++) {
            System.out.println(listSigner.get(counter));

            Signer signer = new Signer();
            signer.setEmail(listSigner.get(counter).getEmail());
            signer.setName(listSigner.get(counter).getName());
            signer.setRecipientId("1");
            signer.setTabs(tabs);

            signers.add(signer);
        }

        /*Signer signer = new Signer();
        signer.setEmail(listSigner);
        signer.setName(signerName);
        signer.recipientId("1");
        signer.setTabs(tabs);*/

        /*Agent agents = new Agent();
        agents.setEmail("basoh45075@huvacliq.com");
        agents.setName("Danilo - Agente");
        agents.recipientId("2");*/

        // Set recipients - Testemunhas
        /*Witness witness = new Witness();
        witness.setEmail("piheceb193@necktai.com");
        witness.setName("Danilo - Testemunhas");
        witness.recipientId("3");
        witness.setTabs(tabs);
        witness.setWitnessFor("1");*/



        Recipients recipients = new Recipients();
        //recipients.setSigners(Arrays.asList(signer));
        recipients.setSigners(signers);
        //recipients.setAgents(Arrays.asList(agents));
        //recipients.setWitnesses(Arrays.asList(witness));

        envelope.setRecipients(recipients);

        // Add document
        Document document = new Document();
        //document.setDocumentBase64("VGhhbmtzIGZvciByZXZpZXdpbmcgdGhpcyEKCldlJ2xsIG1vdmUgZm9yd2FyZCBhcyBzb29uIGFzIHdlIGhlYXIgYmFjay4=");
        document.setDocumentBase64(docBase64);
        document.setName(docName+".pdf");
        document.setFileExtension("pdf");
        document.setDocumentId(docId);
        envelope.setDocuments(Arrays.asList(document));

        //envelope.setStatus("sent");
        envelope.setStatus("created");

        EnvelopeSummary results = envelopesApi.createEnvelope(accountId, envelope);
        return results.getEnvelopeId();

    }
}
