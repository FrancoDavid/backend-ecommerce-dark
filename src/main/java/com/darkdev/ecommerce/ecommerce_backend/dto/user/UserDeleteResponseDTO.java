package com.darkdev.ecommerce.ecommerce_backend.dto.user;

public class UserDeleteResponseDTO {
    private Integer idUser;

    public UserDeleteResponseDTO(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
