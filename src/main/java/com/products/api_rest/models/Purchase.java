package com.products.api_rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;

    @ManyToMany
    @JoinTable(
            name = "album_purchase",
            joinColumns = {@JoinColumn(name = "purchase_id", referencedColumnName = "purchaseId")},
            inverseJoinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")}
    )
    private List<Album> albums = new ArrayList<>();
    @NotNull
    @Enumerated
    @Column(name = "purchase_status")
    private PurchaseStatus status;
    @NotNull
    private BigDecimal price;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd:mm:ss:SSSZ")
    private Date purchaseDateTime = new Date();
    @NotNull
    private boolean premium;

    // Getters and Setters
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public @NotNull PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull PurchaseStatus status) {
        this.status = status;
    }

    public @NotNull BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull BigDecimal price) {
        this.price = price;
    }

    public @NotNull Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(@NotNull Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(@NotNull boolean premium) {
        this.premium = premium;
    }
}
