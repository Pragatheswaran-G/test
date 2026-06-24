    package com.example.LoginAndRegister.repo;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import com.example.LoginAndRegister.model.Product;

    @Repository
    public interface Productrepo extends JpaRepository<Product,Long> {

        
    }
