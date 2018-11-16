package br.com.fakebank.representations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.Pessoa;
import br.com.fakebank.util.ListaPaginada;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Classe de modelo (representação de cliente).")
public class ClienteRepresentationV1 {

    @ApiModelProperty(notes = "Código do cliente (autonumerável).")
   private Integer codigo;
    @ApiModelProperty(notes = "Código da pessoa.")
    private Pessoa pessoa;    
    @ApiModelProperty(notes = "Indicacao de Ativo.")
    private boolean isAtivo;
    @ApiModelProperty(notes = "Endereco do cliente.")
   private String endereco;
    

   public static ClienteRepresentationV1 from(Cliente cliente){
       ClienteRepresentationV1 model = new ClienteRepresentationV1();
       model.codigo = cliente.getCodigo();
       model.pessoa = cliente.getPessoa();
       model.isAtivo = cliente.isAtivo();
       model.endereco= cliente.getEndereco();
       return model;
   }

   public static List<ClienteRepresentationV1> from(List<Cliente> clientes){
       return
           clientes
               .stream()
               .map(item -> from(item))
               .collect(Collectors.toList());
   }

   public static ListaPaginada<ClienteRepresentationV1> from(Page<Cliente> clientes){

       ListaPaginada<ClienteRepresentationV1> lista =
               new ListaPaginada<ClienteRepresentationV1>();

       lista.setContent(clientes
                           .stream()
                           .map(item -> from(item))
                           .collect(Collectors.toList()));

       lista.setTotalPages(clientes.getTotalPages());
       lista.setPageNumber(clientes.getPageable().getPageNumber());
       lista.setPageSize(clientes.getPageable().getPageSize());

       return lista;
   }

   public Integer getCodigo() {
       return codigo;
   }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public String getEndereco() {
        return endereco;
    }

}