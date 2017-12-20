package ru.tpu.javaEElabs.lab1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProductInfoClient extends JDialog  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Создаем DAO-объект
	ProductDAO productDAO = new ProductDAO();
	
	// Объявление элементов управления
	private JLabel lbSelectAuthor = new JLabel("Выбор товара по Автору");
	private JLabel lbId = new JLabel("Id");
	private JLabel lbGenre = new JLabel("Жанр");
	private JLabel lbAuthor = new JLabel("ФИО автора");
	private JLabel lbTitle = new JLabel("Название");
	
	private JComboBox comboAuthor = new JComboBox();
	private JTextField txtId = new JTextField();
	private JTextField txtGenre = new JTextField();
	private JTextField txtAuthor = new JTextField();
	private JTextField txtTitle = new JTextField();
	
	private JButton btnAdd = new JButton("Добавить");
	private JButton btnUpdate = new JButton("Обновить");
	private JButton btnRemove = new JButton("Удалить");
	private JButton btnClear = new JButton("Очистить");
	

	/**
	 * Создает экземпляр диалога
	 * @param args
	 */
	public static void main(String[] args) {
		new ProductInfoClient();
	}
	/**
	 * Конструктор диалога
	 */
	public ProductInfoClient() {
		this.setTitle("Информация о товарах");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(8, 2));
		this.setBounds(100, 50, 400, 200);

		// Добавление элементов управления в диалог
		this.add(lbSelectAuthor);
		this.add(comboAuthor);
		this.add(lbId);
		this.add(txtId);		
		this.add(lbGenre);
		this.add(txtGenre);
		this.add(lbAuthor);
		this.add(txtAuthor);
		this.add(lbTitle);
		this.add(txtTitle);
		this.add(btnAdd);
		this.add(btnUpdate);
		this.add(btnRemove);
		this.add(btnClear);
		
		// Описание обработчиков событий
		comboAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProductData();
			}			
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}			
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProduct();
			}			
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeProduct();
			}			
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearProductInfo();
			}			
		});	
		
		// Обновляем список идентификаторов товаров
		refreshIdList();
		
		// Отображаем диалог на экране
		this.setVisible(true);
	}
	
	/**
	 * Считывает список идентификаторов товаров 
	 * и заполняет список
	 */
	private void refreshIdList() {
		try {
			// получаем список идентификаторов
			List<Integer> productIds = productDAO.getProductIds();
			// очищаем список
			comboAuthor.removeAllItems();
			// заполняем список полученными данными
			for (Integer productId: productIds) {
				comboAuthor.addItem(productId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * Отображает данные о товаре по 
	 * выбранному в списке идентификатору
	 */
	protected void showProductData() {
 	  try {
		// забираем значение, выбранное в списке 
// идентификаторов товаров
		Integer productId = (Integer)comboAuthor.getSelectedItem();

		if (productId != null) {
			// получаем товар по идентификатору
			
			List<Product> product = productDAO.getProductById(productId);
			// заполняем текстовые поля значениями 
// параметров товара 
			for(int i=0; i<product.size(); i++){
				txtId.setText(String.valueOf(product.get(i).getId()));
				txtGenre.setText(product.get(i).getGenre());
				txtAuthor.setText(String.valueOf(product.get(i).getAuthor()));
				txtTitle.setText(String.valueOf(product.get(i).getTitle()));
			}
		}
	  } catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(this, e.getMessage());
	  }
	}	
	
	/**
	 * Добавляет новый товар на основе 
	 * данных текстовых полей
	 */
	protected void addProduct() {
		try {
			// создаем новый объект-товар
			// на основе данных диалога
			Product product = new Product(
				Integer.parseInt(txtId.getText()),
				txtGenre.getText(),
				txtAuthor.getText(),
				txtTitle.getText());
			// сохраняем товар в БД
			productDAO.addProduct(product);
			// обновляем список идентификаторов
			refreshIdList();
			// устанавливаем текущим добавленный товар 
comboAuthor.setSelectedItem(
txtAuthor.getText());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}		
	}
	
	/**
	 * Обновляет информацию о товаре на основе 
	 * данных текстовых полей
	 */	
	protected void updateProduct() {
		try {			
			// формируем объект-товар
			// на основе данных диалога 
			Product product = new Product(
					Integer.parseInt(txtId.getText()),
					txtGenre.getText(),
					txtAuthor.getText(),
					txtTitle.getText());
			// обновляем данные о товаре в БД 
			productDAO.setProduct(product);			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}		
	}

	/**
	 * Удаляет выбранный товар
	 */
	protected void removeProduct() {
		try { 
			// удаляем товар из БД 
			productDAO.removeProduct(
Integer.parseInt(txtId.getText()));
			// обновляем список идентификаторов товаров
			refreshIdList();
			// отображаем данные по первому товару в списке
			showProductData();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}		
	}

	/**
	 * Очищает данные в текстовых полях
	 */
	protected void clearProductInfo() {
		try {
			txtId.setText("");
			txtGenre.setText("");
			txtAuthor.setText("");
			txtTitle.setText("");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}		
	}
}
