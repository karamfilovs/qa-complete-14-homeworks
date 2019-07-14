public class ClientAccount {
    private String firm_name;
    private String firm_addr;
    private String firm_mol;
    private String firm_town;

    public boolean isFirm_is_reg_vat() {
        return firm_is_reg_vat;
    }

    public void setFirm_is_reg_vat(boolean firm_is_reg_vat) {
        this.firm_is_reg_vat = firm_is_reg_vat;
    }

    private boolean firm_is_reg_vat;

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getFirm_addr() {
        return firm_addr;
    }

    public void setFirm_addr(String firm_addr) {
        this.firm_addr = firm_addr;
    }

    public String getFirm_mol() {
        return firm_mol;
    }

    public void setFirm_mol(String firm_mol) {
        this.firm_mol = firm_mol;
    }

    public String getFirm_town() {
        return firm_town;
    }

    public void setFirm_town(String firm_town) {
        this.firm_town = firm_town;
    }
}
