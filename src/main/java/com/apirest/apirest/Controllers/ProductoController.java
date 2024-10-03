package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository; // this.productoRepository = productoRepository

    @GetMapping()
    public List<Producto> getAllProducto() {
        return this.productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con id: " + id));
    }

    @PostMapping()
    public Producto createProducto(@RequestBody Producto producto) {

        return this.productoRepository.save(producto);

    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existingProducto = this.productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con id: " + id));

        existingProducto.setNombre(producto.getNombre());
        existingProducto.setMarca(producto.getMarca());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setStock(producto.getStock());

        return this.productoRepository.save(existingProducto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto existingProducto = this.productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con id: " + id));
        this.productoRepository.deleteById(existingProducto.getId());
        return "Se elimino el producto con id: " + id;
    }


}
